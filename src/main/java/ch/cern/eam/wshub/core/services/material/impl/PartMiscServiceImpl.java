package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.material.entities.IssueReturnPartTransaction;
import ch.cern.eam.wshub.core.services.material.entities.IssueReturnPartTransactionLine;
import ch.cern.eam.wshub.core.services.material.PartMiscService;
import ch.cern.eam.wshub.core.services.material.entities.*;
import ch.cern.eam.wshub.core.services.workorders.WorkOrderService;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import ch.cern.eam.wshub.core.services.workorders.impl.WorkOrderServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.DataTypeTools;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.decodeBoolean;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isNotEmpty;
import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.ResultSet;
import ch.cern.eam.wshub.core.repositories.PartAssociationRepository;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PartMiscServiceImpl implements PartMiscService {

    private Tools tools;

    private ApplicationData applicationData;

    private WorkOrderService workOrderService;

    private PartAssociationRepository partAssociationRepository;

    public PartMiscServiceImpl(ApplicationData applicationData, Tools tools, PartAssociationRepository partAssociationRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.partAssociationRepository = partAssociationRepository;
    }

    public String addPartSupplier(InforContext context, PartSupplier partSupplierParam) throws InforException {
        return null;
    }

    public List<String> createIssueReturnTransaction(InforContext context, List<IssueReturnPartTransaction> issueReturnPartTransactionList) throws InforException {
        List<String> results = new ArrayList<String>();
        for (IssueReturnPartTransaction tr : issueReturnPartTransactionList) {
            results.add(createIssueReturnTransaction(context, tr));
        }
        return results;
    }

    public String createIssueReturnTransaction(InforContext context, IssueReturnPartTransaction issueReturnPartTransaction) throws InforException {
        return null;
        //
        // TRANSACTION TYPE
        // Date
        //
        // TRANSACTION LINEs
        //
        //
        // Manually update the Transaction UserDefined fields here through the db connection since Infor WS does not
    }

    public String createPartAssociation(InforContext context, PartAssociation partAssociation) throws InforException {
        PartAssociation saved = partAssociationRepository.save(partAssociation);
        return saved.getPk();
    }

    public String deletePartAssociation(InforContext context, PartAssociation partAssociation) throws InforException {
        partAssociationRepository.deleteById(partAssociation.getPk());
        return partAssociation.getPk();
    }

    public String createPartSubstitute(InforContext context, PartSubstitute partSubstitute) throws InforException {
        return null;
    }

    public String addStoreBin(InforContext context, Bin binParam) throws InforException {
        return null;
    }

    @Override
    public Bin readStoreBin(InforContext context, Bin binParam) throws InforException {
        return null;
    }

    @Override
    public String updateStoreBin(InforContext context, Bin binParam) throws InforException {
        return null;
    }

    @Override
    public String deleteStoreBin(InforContext context, Bin binParam) throws InforException {
        return null;
    }

    public PartManufacturer[] getPartManufacturers(InforContext context, String partCode) throws InforException {
        LinkedList<PartManufacturer> partManufacturers = new LinkedList<PartManufacturer>();
        String sqlQuery = "select r5partmfgs.mfp_part, r5partmfgs.mfp_manufacturer, r5manufacturers.mfg_desc, r5partmfgs.mfp_manufactpart, r5partmfgs.mfp_manufactdraw, r5partmfgs.mfp_primary, r5partmfgs.mfp_notused from r5partmfgs,r5manufacturers where mfp_manufacturer = mfg_code and mfp_part= '" + partCode + "'";
        try (Connection v_connection = tools.getDataSource().getConnection();
            Statement stmt = v_connection.createStatement();
            ResultSet v_result = stmt.executeQuery(sqlQuery)) {
            while (v_result.next()) {
                PartManufacturer partManufacturer = new PartManufacturer();
                partManufacturer.setPartCode(v_result.getString("mfp_part"));
                partManufacturer.setManufacturerCode(v_result.getString("mfp_manufacturer"));
                partManufacturer.setManufacturerDesc(v_result.getString("mfg_desc"));
                partManufacturer.setManufacturerPartNumber(v_result.getString("mfp_manufactpart"));
                partManufacturer.setDrawingNumber(v_result.getString("mfp_manufactdraw"));
                partManufacturer.setPrimary(decodeBoolean(v_result.getString("mfp_primary")));
                partManufacturer.setOutOfService(decodeBoolean(v_result.getString("mfp_notused")));
                partManufacturers.addLast(partManufacturer);
            }
        } catch (Exception e) {
            throw tools.generateFault("Couldn't read the manufacturers: " + e.getMessage());
        }
        return partManufacturers.toArray(new PartManufacturer[0]);
    }

    @Override
    public String createBin2binTransfer(InforContext context, Bin2BinTransfer bin2BinTransfer) throws InforException {
        return null;
    }

    public BatchResponse<String> createBin2binTransferBatch(InforContext context, List<Bin2BinTransfer> bin2BinTransferList) {
        return tools.batchOperation(context, this::createBin2binTransfer, bin2BinTransferList);
    }

    private String treatCodeSafe(String code) {
        if (code == null)
            return null;
        return code.trim().toUpperCase();
    }
}
