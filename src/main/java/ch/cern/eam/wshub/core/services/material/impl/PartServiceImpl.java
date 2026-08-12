package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.PartRepository;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.material.PartService;
import ch.cern.eam.wshub.core.services.material.entities.Part;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedListService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.EntityId;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedListServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_fields.*;
import net.datastream.schemas.mp_functions.mp0240_001.MP0240_AddPart_001;
import net.datastream.schemas.mp_functions.mp0241_001.MP0241_GetPart_001;
import net.datastream.schemas.mp_functions.mp0242_001.MP0242_SyncPart_001;
import net.datastream.schemas.mp_functions.mp0243_001.MP0243_DeletePart_001;
import net.datastream.schemas.mp_functions.mp0244_001.MP0244_GetPartDefault_001;
import net.datastream.schemas.mp_functions.mp2072_001.ChangePartNumber;
import net.datastream.schemas.mp_functions.mp2072_001.MP2072_ChangePartNumber_001;
import net.datastream.schemas.mp_results.mp0240_001.MP0240_AddPart_001_Result;
import net.datastream.schemas.mp_results.mp0241_001.MP0241_GetPart_001_Result;
import net.datastream.schemas.mp_results.mp0242_001.MP0242_SyncPart_001_Result;
import net.datastream.schemas.mp_results.mp0244_001.MP0244_GetPartDefault_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import java.util.HashMap;
import java.util.List;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;
import static ch.cern.eam.wshub.core.tools.Tools.extractEntityCode;
import static ch.cern.eam.wshub.core.tools.Tools.extractOrganizationCode;

public class PartServiceImpl implements PartService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private UserDefinedListService userDefinedListService;

    private PartRepository partRepository;

    public PartServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public PartServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, PartRepository partRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.partRepository = partRepository;
        this.userDefinedListService = new UserDefinedListServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
    }

    //
    // BATCH WEB SERVICES
    //
    public BatchResponse<String> createPartBatch(InforContext context, List<Part> parts) {
        return tools.batchOperation(context, this::createPart, parts);
    }

    public BatchResponse<Part> readPartBatch(InforContext context, List<String> partCodes) {
        return tools.batchOperation(context, this::readPart, partCodes);
    }

    public BatchResponse<String> updatePartBatch(InforContext context, List<Part> parts) {
        return tools.batchOperation(context, this::updatePart, parts);
    }

    public BatchResponse<String> deletePartBatch(InforContext context, List<String> partCodes) {
        return tools.batchOperation(context, this::deletePart, partCodes);
    }

    //
    //
    //
    public Part readPartDefault(InforContext context, String organization) throws InforException {
        return partRepository.findById(organization).orElse(null);
    }

    public Part readPart(InforContext context, String partCode) throws InforException {
        String code = extractEntityCode(partCode);
        return partRepository.findById(code).orElse(null);
    }

    private net.datastream.schemas.mp_entities.part_001.Part readPartInfor(InforContext context, String partCode, String organization) throws InforException {
        MP0241_GetPart_001 getPart = new MP0241_GetPart_001();
        getPart.setPARTID(new PARTID_Type());
        getPart.getPARTID().setORGANIZATIONID(tools.getOrganization(context, organization));
        getPart.getPARTID().setPARTCODE(partCode);
        MP0241_GetPart_001_Result getPartResult = tools.performInforOperation(context, inforws::getPartOp, getPart);
        return getPartResult.getResultData().getPart();
    }

    public String createPart(InforContext context, Part partParam) throws InforException {
        Part saved = partRepository.save(partParam);
        return saved.getCode();
        //
        //
    }

    public String updatePart(InforContext context, Part partParam) throws InforException {
        Part saved = partRepository.save(partParam);
        return saved.getCode();
        //
        // UPDATE PART
    }

    public String deletePart(InforContext context, String partCode) throws InforException {
        partRepository.deleteById(partCode);
        return partCode;
    }
}
