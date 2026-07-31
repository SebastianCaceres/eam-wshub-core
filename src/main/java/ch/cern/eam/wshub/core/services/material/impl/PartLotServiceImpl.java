package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartLotService;
import ch.cern.eam.wshub.core.services.material.entities.Lot;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_fields.LOTID_Type;
import net.datastream.schemas.mp_functions.mp1201_001.MP1201_AddLot_001;
import net.datastream.schemas.mp_functions.mp1202_001.MP1202_SyncLot_001;
import net.datastream.schemas.mp_functions.mp1203_001.MP1203_DeleteLot_001;
import net.datastream.schemas.mp_functions.mp1205_001.MP1205_GetLot_001;
import net.datastream.schemas.mp_results.mp1202_001.MP1202_SyncLot_001_Result;
import net.datastream.schemas.mp_results.mp1203_001.MP1203_DeleteLot_001_Result;
import net.datastream.schemas.mp_results.mp1205_001.MP1205_GetLot_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;

import ch.cern.eam.wshub.core.repositories.LotRepository;
import java.util.Optional;

public class PartLotServiceImpl implements PartLotService {

    private Tools tools;
    private InforWebServicesPT inforws;
    private ApplicationData applicationData;
    private LotRepository lotRepository;

    public PartLotServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public PartLotServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, LotRepository lotRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.lotRepository = lotRepository;
    }

    @Override
    public String createLot(InforContext context, Lot lot) throws InforException {
        if (lotRepository != null) {
            try {
                Lot saved = lotRepository.save(lot);
                return saved.getCode();
            } catch (Exception e) {
                // Fallback to SOAP
            }
        }

        net.datastream.schemas.mp_entities.lot_001.Lot lotInfor = new net.datastream.schemas.mp_entities.lot_001.Lot();
        tools.getInforFieldTools().transformWSHubObject(lotInfor, lot, context);
        MP1201_AddLot_001 addLot = new MP1201_AddLot_001();
        addLot.setLot(lotInfor);
        tools.performInforOperation(context, inforws::addLotOp, addLot);
        return lot.getCode();
    }

    @Override
    public Lot readLot(InforContext context, String lotPk) throws InforException {
        if (lotRepository != null && lotPk != null) {
            Optional<Lot> lot = lotRepository.findById(lotPk);
            if (lot.isPresent()) {
                return lot.get();
            }
        }

        net.datastream.schemas.mp_entities.lot_001.Lot lot = readLotInfor(context, lotPk);

        return tools.getInforFieldTools().transformInforObject(new Lot(), lot, context);

    }

    @Override
    public String updateLot(InforContext context, Lot lot) throws InforException {
        if (lotRepository != null) {
            try {
                Lot saved = lotRepository.save(lot);
                return saved.getCode();
            } catch (Exception e) {
                // Fallback to SOAP
            }
        }

        MP1202_SyncLot_001 syncLot = new MP1202_SyncLot_001();

        net.datastream.schemas.mp_entities.lot_001.Lot prev = readLotInfor(context, lot.getCode());
        tools.getInforFieldTools().transformWSHubObject(prev, lot, context);
        syncLot.setLot(prev);

        MP1202_SyncLot_001_Result result = tools.performInforOperation(context, inforws::syncLotOp, syncLot);
        return  result.getResultData().getLot().getLOTID().getLOTCODE();
    }

    @Override
    public String deleteLot(InforContext context, String lotCode) throws InforException {
        if (lotRepository != null && lotCode != null) {
            try {
                lotRepository.deleteById(lotCode);
                return lotCode;
            } catch (Exception e) {
                // Fallback to SOAP
            }
        }

        MP1203_DeleteLot_001 deleteLot = new MP1203_DeleteLot_001();
        LOTID_Type idType = new LOTID_Type();
        idType.setLOTCODE(lotCode);
        idType.setORGANIZATIONID(tools.getOrganization(context));

        deleteLot.setLOTID(idType);
        MP1203_DeleteLot_001_Result result = tools.performInforOperation(context, inforws::deleteLotOp, deleteLot);
        return result.getResultData().getLOTID().getLOTCODE();
    }


    private net.datastream.schemas.mp_entities.lot_001.Lot readLotInfor(
            InforContext context, String lotCode) throws InforException {
        MP1205_GetLot_001 getLot = new MP1205_GetLot_001();
        LOTID_Type idType = new LOTID_Type();
        idType.setLOTCODE(lotCode);
        idType.setORGANIZATIONID(tools.getOrganization(context));
        getLot.setLOTID(idType);

        MP1205_GetLot_001_Result result =
                tools.performInforOperation(context, inforws::getLotOp, getLot);

        return result.getResultData().getLot();
    }

}

