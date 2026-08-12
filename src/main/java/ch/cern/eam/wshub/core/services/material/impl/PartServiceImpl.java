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
import java.util.HashMap;
import java.util.List;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;
import static ch.cern.eam.wshub.core.tools.Tools.extractEntityCode;
import static ch.cern.eam.wshub.core.tools.Tools.extractOrganizationCode;

public class PartServiceImpl implements PartService {

    private Tools tools;

    private ApplicationData applicationData;

    private UserDefinedListService userDefinedListService;

    private PartRepository partRepository;

    public PartServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public PartServiceImpl(ApplicationData applicationData, Tools tools, PartRepository partRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.partRepository = partRepository;
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
