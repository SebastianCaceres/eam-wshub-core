package ch.cern.eam.wshub.core.services.administration.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.DataspyCustomFieldRepository;
import ch.cern.eam.wshub.core.repositories.DataspyFieldRepository;
import ch.cern.eam.wshub.core.services.administration.DataspyService;
import ch.cern.eam.wshub.core.services.administration.entities.DataspyCopy;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestResult;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.math.BigDecimal;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.encodeQuantity;
import static ch.cern.eam.wshub.core.tools.GridTools.extractSingleResult;

public class DataspyServiceImpl implements DataspyService {

    private Tools tools;

    private ApplicationData applicationData;

    private GridsService gridsService;

    private DataspyCustomFieldRepository dataspyCustomFieldRepository;

    private DataspyFieldRepository dataspyFieldRepository;

    public DataspyServiceImpl(ApplicationData applicationData, Tools tools, DataspyCustomFieldRepository dataspyCustomFieldRepository, DataspyFieldRepository dataspyFieldRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.dataspyCustomFieldRepository = dataspyCustomFieldRepository;
        this.dataspyFieldRepository = dataspyFieldRepository;
    }

    public String copyDataspy(InforContext context, DataspyCopy dataspyCopy) throws InforException {
        return null;
        // Uncomment once Infor WS returns the ID of the created dataspy and not the one used as a source
        //return tools.getDataTypeTools().decodeQuantity(result.getResultData().getSCREENDATASPYID().getDDSPYID());
        // Temporarily fetch the ID of the created dataspy (most recent dataspy created for the passed user)
    }

    private String getLastDataspy(InforContext context, String userCode) throws InforException {
        GridRequest gridRequest = new GridRequest("BEWSDP", 1);
        gridRequest.addFilter("dds_owner", userCode, "=");
        gridRequest.sortBy("dds_ddspyid", "DESC");
        final GridRequestResult gridRequestResult = gridsService.executeQuery(context, gridRequest);
        return extractSingleResult(gridRequestResult, "dds_ddspyid");
    }

    public String deleteDataspy(InforContext context, BigDecimal dataspyId) throws InforException {
        return null;
    }

    public java.util.List<ch.cern.eam.wshub.core.services.grids.customfields.DataspyCustomField> readDataspyCustomFields(InforContext context, String dataspyId) throws InforException {
        if (dataspyCustomFieldRepository == null) {
            throw tools.generateFault("Database connection required for readDataspyCustomFields");
        }
        return dataspyCustomFieldRepository.findByDataspyID(dataspyId);
    }

    public java.util.List<ch.cern.eam.wshub.core.services.grids.entities.DataspyField> readDataspyFields(InforContext context, String dataspyId) throws InforException {
        if (dataspyFieldRepository == null) {
            throw tools.generateFault("Database connection required for readDataspyFields");
        }
        return dataspyFieldRepository.findByDataspy(dataspyId);
    }
}
