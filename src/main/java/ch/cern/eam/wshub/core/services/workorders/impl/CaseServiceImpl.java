package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.CaseService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.workorders.entities.InforCase;
import net.datastream.schemas.mp_entities.casemanagement_001.CaseDetails;
import net.datastream.schemas.mp_entities.casemanagement_001.CaseManagement;
import net.datastream.schemas.mp_entities.casemanagement_001.TrackingDetails;
import net.datastream.schemas.mp_fields.*;
import net.datastream.schemas.mp_functions.SessionType;
import net.datastream.schemas.mp_functions.mp3640_001.MP3640_AddCaseManagement_001;
import net.datastream.schemas.mp_functions.mp3641_001.MP3641_SyncCaseManagement_001;
import net.datastream.schemas.mp_functions.mp3642_001.MP3642_DeleteCaseManagement_001;
import net.datastream.schemas.mp_functions.mp3643_001.MP3643_GetCaseManagement_001;
import net.datastream.schemas.mp_results.mp3640_001.MP3640_AddCaseManagement_001_Result;
import net.datastream.schemas.mp_results.mp3641_001.MP3641_SyncCaseManagement_001_Result;
import net.datastream.schemas.mp_results.mp3643_001.MP3643_GetCaseManagement_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import ch.cern.eam.wshub.core.repositories.InforCaseRepository;
import java.util.Optional;
import javax.xml.ws.Holder;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class CaseServiceImpl implements CaseService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private InforCaseRepository inforCaseRepository;

    public CaseServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public CaseServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, InforCaseRepository inforCaseRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.inforCaseRepository = inforCaseRepository;
    }

    public InforCase readCase(InforContext context, String caseID) throws InforException {
        return inforCaseRepository.findById(caseID).orElse(null);
    }

    public String createCase(InforContext context, InforCase caseMT) throws InforException {
        InforCase saved = inforCaseRepository.save(caseMT);
        return saved.getCode();
    }

    public String deleteCase(InforContext context, String caseID) throws InforException {
        inforCaseRepository.deleteById(caseID);
        return caseID;
    }

    public synchronized String updateCase(InforContext context, InforCase caseMT) throws InforException {
        InforCase saved = inforCaseRepository.save(caseMT);
        return saved.getCode();
        //
        // FETCH IT FIRST
        //
        // INIT
        //
        // UPDATE
        //
    }

    private void initCaseObject(CaseManagement caseInfor, InforCase caseMT, InforContext context) throws InforException {
        tools.getInforFieldTools().transformWSHubObject(caseInfor, caseMT, context);
        //
        // CODE AND DESCRIPTION
        //
        if (caseInfor.getCASEID() == null) {
            caseInfor.setCASEID(new CASEID_Type());
            caseInfor.getCASEID().setORGANIZATIONID(tools.getOrganization(context));
            caseInfor.getCASEID().setCASECODE("0");
        }
        if (caseMT.getDescription() != null) {
            caseInfor.getCASEID().setDESCRIPTION(caseMT.getDescription());
        }
        //
        // EQUIPMENT
        //
        if (caseMT.getEquipmentCode() != null) {
            if (caseMT.getEquipmentCode().trim().equals("")) {
                caseInfor.setEQUIPMENTID(null);
            } else {
                caseInfor.setEQUIPMENTID(new EQUIPMENTID_Type());
                caseInfor.getEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
                caseInfor.getEQUIPMENTID().setEQUIPMENTCODE(caseMT.getEquipmentCode().toUpperCase().trim());
            }
        }
        //
        // STATUS
        //
        if (caseMT.getStatusCode() != null) {
            caseInfor.setSTATUS(new STATUS_Type());
            caseInfor.getSTATUS().setSTATUSCODE(caseMT.getStatusCode().toUpperCase().trim());
            caseInfor.setRSTATUS(new STATUS_Type());
            caseInfor.getRSTATUS().setSTATUSCODE(caseMT.getStatusCode().toUpperCase().trim());
        }
        //
        // TYPE
        //
        if (caseMT.getTypeCode() != null) {
            caseInfor.setCASETYPE(new TYPE_Type());
            caseInfor.getCASETYPE().setTYPECODE(caseMT.getTypeCode().toUpperCase().trim());
        }
        //
        // DEPARTMENT
        //
        if (caseMT.getDepartmentCode() != null) {
            caseInfor.setDEPARTMENTID(new DEPARTMENTID_Type());
            caseInfor.getDEPARTMENTID().setORGANIZATIONID(tools.getOrganization(context));
            caseInfor.getDEPARTMENTID().setDEPARTMENTCODE(caseMT.getDepartmentCode().toUpperCase().trim());
        }
        //
        // CLASS CODE AND DESCRIPTION
        //
        if (caseMT.getClassCode() != null) {
            if (caseInfor.getCaseDetails() == null)
                caseInfor.setCaseDetails(new CaseDetails());
            if (caseInfor.getCaseDetails().getCASECLASSID() == null) {
                caseInfor.getCaseDetails().setCASECLASSID(new CLASSID_Type());
            }
            caseInfor.getCaseDetails().getCASECLASSID().setCLASSCODE(caseMT.getClassCode());
            caseInfor.getCaseDetails().getCASECLASSID().setDESCRIPTION(caseMT.getClassDesc());
            caseInfor.getCaseDetails().getCASECLASSID().setORGANIZATIONID(tools.getOrganization(context));
            if (caseMT.getPriority() != null) {
                USERDEFINEDCODEID_Type t = new USERDEFINEDCODEID_Type();
                caseInfor.getCaseDetails().setCASEPRIORITY(new USERDEFINEDCODEID_Type());
                caseInfor.getCaseDetails().getCASEPRIORITY().setUSERDEFINEDCODE(caseMT.getPriority());
            }
        }
        //
        // WORK ADDRESS
        //
        if (caseMT.getWorkaddress() != null) {
            if (caseInfor.getCaseDetails() == null)
                caseInfor.setCaseDetails(new CaseDetails());
            caseInfor.getCaseDetails().setWORKADDRESS(caseMT.getWorkaddress());
        }
        //
        // RESPONSIBLE
        //
        if (caseMT.getResponsibleCode() != null) {
            if (caseInfor.getTrackingDetails() == null)
                caseInfor.setTrackingDetails(new TrackingDetails());
            if (caseInfor.getTrackingDetails().getPERSONRESPONSIBLE() == null)
                caseInfor.getTrackingDetails().setPERSONRESPONSIBLE(new Employee_Type());
            caseInfor.getTrackingDetails().getPERSONRESPONSIBLE().setEMPLOYEECODE(caseMT.getResponsibleCode());
            caseInfor.getTrackingDetails().getPERSONRESPONSIBLE().setDESCRIPTION(caseMT.getResponsibleDesc());
            caseInfor.getTrackingDetails().setEMAIL(caseMT.getResponsibleEMail());
        }
        //
        // ASSIGNED TO
        //
        if (caseMT.getAssignedToCode() != null) {
            if (caseInfor.getTrackingDetails() == null)
                caseInfor.setTrackingDetails(new TrackingDetails());
            if (caseInfor.getTrackingDetails().getASSIGNEDTO() == null)
                caseInfor.getTrackingDetails().setASSIGNEDTO(new PERSONID_Type());
            caseInfor.getTrackingDetails().getASSIGNEDTO().setPERSONCODE(caseMT.getAssignedToCode());
            caseInfor.getTrackingDetails().getASSIGNEDTO().setDESCRIPTION(caseMT.getAssignedToDesc());
            caseInfor.getTrackingDetails().setASSIGNEDTOEMAIL(caseMT.getAssignedToEMail());
        }
        //
        // LOCATION
        //
        if (caseMT.getLocationCode() != null) {
            if (caseInfor.getCaseDetails() == null) {
                caseInfor.setCaseDetails(new CaseDetails());
            }
            if (caseMT.getLocationCode().equals("")) {
                caseInfor.getCaseDetails().setLOCATIONID(null);
            } else {
                caseInfor.getCaseDetails().setLOCATIONID(new LOCATIONID_Type());
                caseInfor.getCaseDetails().getLOCATIONID().setORGANIZATIONID(tools.getOrganization(context));
                caseInfor.getCaseDetails().getLOCATIONID().setLOCATIONCODE(caseMT.getLocationCode().trim());
            }
        }
        //
        // SCHEDULING START DATE
        //
        if (caseMT.getScheduledStartDate() != null) {
            if (caseInfor.getTrackingDetails() == null) {
                caseInfor.setTrackingDetails(new TrackingDetails());
            }
            caseInfor.getTrackingDetails().setSCHEDULEDSTARTDATE(tools.getDataTypeTools().encodeInforDate(caseMT.getScheduledStartDate(), "Scheduling Start Date"));
        }
        //
        // SCHEDULING END DATE
        //
        if (caseMT.getScheduledEndDate() != null) {
            if (caseInfor.getTrackingDetails() == null) {
                caseInfor.setTrackingDetails(new TrackingDetails());
            }
            caseInfor.getTrackingDetails().setSCHEDULEDENDDATE(tools.getDataTypeTools().encodeInforDate(caseMT.getScheduledEndDate(), "Scheduling Completed Date"));
        }
        //
        // REQUESTED START DATE
        //
        if (caseMT.getRequestedStartDate() != null) {
            if (caseInfor.getTrackingDetails() == null) {
                caseInfor.setTrackingDetails(new TrackingDetails());
            }
            caseInfor.getTrackingDetails().setREQUESTEDSTART(tools.getDataTypeTools().encodeInforDate(caseMT.getRequestedStartDate(), "Requested Start Date"));
        }
        //
        // REQUESTED END DATE
        //
        if (caseMT.getRequestedEndDate() != null) {
            if (caseInfor.getTrackingDetails() == null) {
                caseInfor.setTrackingDetails(new TrackingDetails());
            }
            caseInfor.getTrackingDetails().setREQUESTEDEND(tools.getDataTypeTools().encodeInforDate(caseMT.getRequestedEndDate(), "Requested End Date"));
        }
        //
        // START DATE
        //
        if (caseMT.getStartDate() != null) {
            if (caseInfor.getTrackingDetails() == null) {
                caseInfor.setTrackingDetails(new TrackingDetails());
            }
            caseInfor.getTrackingDetails().setSTARTDATE(tools.getDataTypeTools().encodeInforDate(caseMT.getStartDate(), "Start Date"));
        }
        //
        // COMPLETED DATE
        //
        if (caseMT.getCompletedDate() != null) {
            if (caseInfor.getTrackingDetails() == null) {
                caseInfor.setTrackingDetails(new TrackingDetails());
            }
            caseInfor.getTrackingDetails().setCOMPLETEDDATE(tools.getDataTypeTools().encodeInforDate(caseMT.getCompletedDate(), "Completed Date"));
        }
        //
        // DATEREQUESTED
        //
        if (caseMT.getDaterequested() != null) {
            if (caseInfor.getTrackingDetails() == null)
                caseInfor.setTrackingDetails(new TrackingDetails());
            caseInfor.getTrackingDetails().setDATEREQUESTED(tools.getDataTypeTools().encodeInforDate(caseMT.getDaterequested(), "Requested Date"));
        }
        //
        // CASE DETAILS EVENT START DATE
        //
        if (caseMT.getEventstartdate() != null) {
            if (caseInfor.getCaseDetails() == null)
                caseInfor.setCaseDetails(new CaseDetails());
            caseInfor.getCaseDetails().setEVENTSTARTDATE(tools.getDataTypeTools().encodeInforDate(caseMT.getEventstartdate(), "Event Start Date"));
        }
        //
        // CASE DETAILS EVENT END DATE
        //
        if (caseMT.getEventenddate() != null) {
            if (caseInfor.getCaseDetails() == null)
                caseInfor.setCaseDetails(new CaseDetails());
            caseInfor.getCaseDetails().setEVENTENDDATE(tools.getDataTypeTools().encodeInforDate(caseMT.getEventenddate(), "Event End Date"));
        }
    }
}
