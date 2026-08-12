package ch.cern.eam.wshub.core.services.material.impl;

import javax.xml.ws.Holder;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import ch.cern.eam.wshub.core.services.material.PurchaseOrdersService;
import ch.cern.eam.wshub.core.services.material.entities.PurchaseOrder;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.tools.InforException;

public class PurchaseOrdersImpl implements PurchaseOrdersService {

    private Tools tools;

    private ApplicationData applicationData;

    public PurchaseOrdersImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String updatePurchaseOrder(InforContext context, PurchaseOrder purchaseOrderParam) throws InforException {
        return null;
        //
        //
        // SET ALL PROPERTIES
        //
        // CALL INFOR WEB SERVICE
    }

    private void initializeInforPOObject(net.datastream.schemas.mp_entities.purchaseorder_001.UserDefinedFields inforUserDefinedFields, UserDefinedFields userDefinedFields) throws InforException {
        if (userDefinedFields.getUdfchar01() != null) {
            inforUserDefinedFields.setUDFCHAR01(userDefinedFields.getUdfchar01());
        }
        if (userDefinedFields.getUdfchar02() != null) {
            inforUserDefinedFields.setUDFCHAR02(userDefinedFields.getUdfchar02());
        }
        if (userDefinedFields.getUdfchar03() != null) {
            inforUserDefinedFields.setUDFCHAR03(userDefinedFields.getUdfchar03());
        }
        if (userDefinedFields.getUdfchar04() != null) {
            inforUserDefinedFields.setUDFCHAR04(userDefinedFields.getUdfchar04());
        }
        if (userDefinedFields.getUdfchar05() != null) {
            inforUserDefinedFields.setUDFCHAR05(userDefinedFields.getUdfchar05());
        }
        if (userDefinedFields.getUdfchar06() != null) {
            inforUserDefinedFields.setUDFCHAR06(userDefinedFields.getUdfchar06());
        }
        if (userDefinedFields.getUdfchar07() != null) {
            inforUserDefinedFields.setUDFCHAR07(userDefinedFields.getUdfchar07());
        }
        if (userDefinedFields.getUdfchar08() != null) {
            inforUserDefinedFields.setUDFCHAR08(userDefinedFields.getUdfchar08());
        }
        if (userDefinedFields.getUdfchar09() != null) {
            inforUserDefinedFields.setUDFCHAR09(userDefinedFields.getUdfchar09());
        }
        if (userDefinedFields.getUdfchar10() != null) {
            inforUserDefinedFields.setUDFCHAR10(userDefinedFields.getUdfchar10());
        }
        if (userDefinedFields.getUdfchar11() != null) {
            inforUserDefinedFields.setUDFCHAR11(userDefinedFields.getUdfchar11());
        }
        if (userDefinedFields.getUdfchar12() != null) {
            inforUserDefinedFields.setUDFCHAR12(userDefinedFields.getUdfchar12());
        }
        if (userDefinedFields.getUdfchar13() != null) {
            inforUserDefinedFields.setUDFCHAR13(userDefinedFields.getUdfchar13());
        }
        if (userDefinedFields.getUdfchar14() != null) {
            inforUserDefinedFields.setUDFCHAR14(userDefinedFields.getUdfchar14());
        }
        if (userDefinedFields.getUdfchar15() != null) {
            inforUserDefinedFields.setUDFCHAR15(userDefinedFields.getUdfchar15());
        }
        if (userDefinedFields.getUdfchar16() != null) {
            inforUserDefinedFields.setUDFCHAR16(userDefinedFields.getUdfchar16());
        }
        if (userDefinedFields.getUdfchar17() != null) {
            inforUserDefinedFields.setUDFCHAR17(userDefinedFields.getUdfchar17());
        }
        if (userDefinedFields.getUdfchar18() != null) {
            inforUserDefinedFields.setUDFCHAR18(userDefinedFields.getUdfchar18());
        }
        if (userDefinedFields.getUdfchar19() != null) {
            inforUserDefinedFields.setUDFCHAR19(userDefinedFields.getUdfchar19());
        }
        if (userDefinedFields.getUdfchar20() != null) {
            inforUserDefinedFields.setUDFCHAR20(userDefinedFields.getUdfchar20());
        }
        if (userDefinedFields.getUdfchar21() != null) {
            inforUserDefinedFields.setUDFCHAR21(userDefinedFields.getUdfchar21());
        }
        if (userDefinedFields.getUdfchar22() != null) {
            inforUserDefinedFields.setUDFCHAR22(userDefinedFields.getUdfchar22());
        }
        if (userDefinedFields.getUdfchar23() != null) {
            inforUserDefinedFields.setUDFCHAR23(userDefinedFields.getUdfchar23());
        }
        if (userDefinedFields.getUdfchar24() != null) {
            inforUserDefinedFields.setUDFCHAR24(userDefinedFields.getUdfchar24());
        }
        if (userDefinedFields.getUdfchar25() != null) {
            inforUserDefinedFields.setUDFCHAR25(userDefinedFields.getUdfchar25());
        }
        if (userDefinedFields.getUdfchar26() != null) {
            inforUserDefinedFields.setUDFCHAR26(userDefinedFields.getUdfchar26());
        }
        if (userDefinedFields.getUdfchar27() != null) {
            inforUserDefinedFields.setUDFCHAR27(userDefinedFields.getUdfchar27());
        }
        if (userDefinedFields.getUdfchar28() != null) {
            inforUserDefinedFields.setUDFCHAR28(userDefinedFields.getUdfchar28());
        }
        if (userDefinedFields.getUdfchar29() != null) {
            inforUserDefinedFields.setUDFCHAR29(userDefinedFields.getUdfchar29());
        }
        if (userDefinedFields.getUdfchar30() != null) {
            inforUserDefinedFields.setUDFCHAR30(userDefinedFields.getUdfchar30());
        }
    }
}
