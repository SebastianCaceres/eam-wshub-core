package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestResult;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.SafetyService;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import ch.cern.eam.wshub.core.tools.*;
import org.openapplications.oagis_segments.QUANTITY;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SafetyServiceImpl implements SafetyService {

    private Tools tools;

    private ApplicationData applicationData;

    private GridsService gridsService;

    public SafetyServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public BatchResponse<List<Safety>> readSafetiesBatch(InforContext context, String entityType, List<String> entityCode) {
        List<Callable<List<Safety>>> callableList = entityCode.stream().<Callable<List<Safety>>>map(code -> () -> readSafeties(context, entityType, code)).collect(Collectors.toList());
        return tools.processCallables(callableList);
    }

    @Override
    public BatchResponse<String> setSafetiesBatch(InforContext context, String entityType, Map<String, List<Safety>> entityCodeToSafeties) {
        List<Callable<String>> callableList = entityCodeToSafeties.keySet().stream().<Callable<String>>map(code -> () -> setSafeties(context, entityType, code, entityCodeToSafeties.get(code))).collect(Collectors.toList());
        return tools.processCallables(callableList);
    }

    // The entityType argument takes either "EVNT" or "OBJ"
    // Note that this method does not return the user defined fields, use the readSafety method for now to get these
    @Override
    public List<Safety> readSafeties(InforContext context, String entityType, String entityCode) throws InforException {
        GridRequest request = new GridRequest();
        request.setGridType(GridRequest.GRIDTYPE.LIST);
        if (isWorkOrder(entityType)) {
            request.setGridName("WSJOBS_KSF");
            request.addParam("param.workordernum", entityCode);
            request.addParam("parameter.r5role", "");
            request.setUserFunctionName("WSJOBS");
        } else if (isObject(entityType)) {
            request.setGridName("OSOBJA_ESF");
            request.setUserFunctionName("OSOBJA");
            request.addParam("parameter.object", entityCode);
            request.addParam("parameter.objorganization", tools.getOrganizationCode(context));
        } else {
            throw Tools.generateFault("Invalid entityType");
        }
        GridRequestResult result = gridsService.executeQuery(context, request);
        List<Safety> safeties = GridTools.convertGridResultToObject(Safety.class, null, result);
        safeties.stream().forEach(safety -> safety.setUserDefinedFields(null));
        return safeties;
    }

    // The entityType argument takes either "EVNT" or "OBJ"
    @Override
    public String setSafeties(InforContext context, String entityType, String entityCode, List<Safety> safeties) throws InforException {
        return "OK";
    }

    public String synchronizeSafety(InforContext context, String entityType, String entityCode, List<Safety> safeties) throws InforException {
        return "OK";
    }

    @Override
    public Safety readSafety(InforContext context, String entityType, String safetyCode) throws InforException {
        return null;
    }

    private String getFullEntityCode(InforContext context, String entityType, String entityCode) {
        String extension = isObject(entityType) ? "#" + tools.getOrganizationCode(context) : "";
        return entityCode + extension;
    }

    private QUANTITY getRevisionQUANTITY(InforContext context, Safety safety, QUANTITY oldQuantity, boolean isHazard) throws InforException {
        String id = isHazard ? safety.getHazardCode() : safety.getPrecautionCode();
        String label = isHazard ? "Hazard Code" : "Precaution Code";
        return oldQuantity == null ? DataTypeTools.encodeQuantity(getLatestRevision(context, id, isHazard), label) : oldQuantity;
    }

    private BigDecimal getLatestRevision(InforContext context, String id, boolean isHazard) throws InforException {
        if (id == null) {
            throw Tools.generateFault(isHazard ? "Hazard Code is null" : "Precaution Code is null");
        }
        GridRequest gridRequest = new GridRequest(isHazard ? "LVSAFETYHAZARD" : "LVPRECAUTION", GridRequest.GRIDTYPE.LOV);
        gridRequest.addFilter(isHazard ? "hazardcode" : "precaution", id, "=");
        // there should be always either one row, or no row at all
        gridRequest.setRowCount(1);
        String revision = GridTools.extractSingleResult(gridsService.executeQuery(context, gridRequest), "revision");
        return new BigDecimal(revision);
    }

    private Map<String, Safety> toMap(List<Safety> safeties) {
        return safeties.stream().collect(Collectors.toMap(Safety::getId, Function.identity(), // if there's more than one safety, keep the first
        (firstSafety, otherSafety) -> firstSafety));
    }

    private boolean isWorkOrder(String entityType) {
        return entityType.equals("EVNT");
    }

    private boolean isObject(String entityType) {
        return entityType.equals("OBJ");
    }
}
