package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.annotations.GridField;
import ch.cern.eam.wshub.core.services.entities.CustomField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NonConformityObservation  {

    // Panel 1
    @GridField(name = "nonconformitypk")
    
    private String observationPk;

    @GridField(name = "organization")
    
    private String organizationCode;

    @GridField(name = "description")
    
    private String description;

    @GridField(name = "nonconformity")
    
    private String nonConformityCode;

    @GridField(name = "nonconformity")
    
    private String code;

    //Panel Nonconformity Details
    @GridField(name = "equipment")
    
    private String equipmentCode;

    @GridField(name = "equipmentorg")
    
    private String equipmentOrg;

    @GridField(name = "locationdesc")
    
    private String locationDesc;

    @GridField(name = "location")
    
    private String locationCode;

    @GridField(name = "locationorg")
    
    private String locationOrgCode;

    @GridField(name = "department")
    
    private String department;

    @GridField(name = "part")
    
    private String partCode;

    @GridField(name = "partdesc")
    
    private String partDesc;

    @GridField(name = "type")
    
    private String typeCode;

    @GridField(name = "typedesc")
    
    private String typeDesc;

    @GridField(name = "typeorg")
    
    private String typeOrgCode;

    @GridField(name = "classCode")
    
    private String classCode;

    @GridField(name = "classorg")
    
    private String classOrgCode;

    @GridField(name = "materialtype")
    
    private String materialType;

    @GridField(name = "status")
    
    private String statusCode;

    @GridField(name = "priority")
    
    private String priority;

    @GridField(name = "syslevel")
    
    private String syslevel;

    @GridField(name = "asslevel")
    
    private String asslevel;

    @GridField(name = "complevel")
    
    private String complevel;

    @GridField(name = "aspectcode")
    
    private String aspectCode;

    @GridField(name = "aspectdescription")
    
    private String aspectDescription;

    @GridField(name = "nonconformitynote")
    
    private String nonConformityNote;

    //Panel observation details
    @GridField(name = "observation")
    
    private String observationNum;

    @GridField(name = "rstatus")
    
    private String observationStatusCode;

    @GridField(name = "taskplanchlist")
    
    private String taskCode;

    @GridField(name = "taskplanchlistrev\n")
    
    private String taskRevision;

    @GridField(name = "note")
    
    private String note;

    @GridField(name = "recordedby")
    
    private String employeeCode;

    @GridField(name = "created")
    
    private String recoredDate;

    @GridField(name = "severity_display")
    
    private String severity;

    @GridField(name = "intensity_display")
    
    private String intensity;

    @GridField(name = "ncfsize")
    
    private String size;

    @GridField(name = "importance")
    
    private String importance;

    @GridField(name = "workordernum")
    
    private String jobNum;

    @GridField(name = "nonconformitybeforemerge")
    
    private String beforeMerge;

    @GridField(name = "massacknowledgeddesc")
    
    private String acknowledgedDescription;

    @GridField(name = "acknowledgedby")
    
    private String acknowledgedBy;

    @GridField(name = "dateacknowledged")
    
    private String acknowledgedDate;

    @GridField(name = "mobiledateacknowledged")
    
    private String mobileDateAcknowledged;

    @GridField(name = "acknowledgedcopiedfromdes")
    
    private String acknowledgedCopiedFrom;

    @GridField(name = "acknowledgedsourceobservseq")
    
    private String acknowledgedSourceObservation;

    @GridField(name = "conditionscore")
    
    private BigDecimal conditionScore;

    @GridField(name = "conditionindex")
    
    private String conditionDescription;

    @GridField(name = "nextinspectdate")
    
    private Date nextInspectDate;

    @Setter
    @GridField(name = "nextinspectdateoverride")
    
    private Date nextInspectDateOverride;

    @GridField(name = "repairdate")
    
    private Date repairDate;

    @GridField(name = "ncfestlaborcost")
    
    private BigDecimal ncfestlaborcost;

    @GridField(name = "ncfestmatlcost")
    
    private BigDecimal ncfestmatlcost;

    @GridField(name = "ncfestmisccost")
    
    private BigDecimal ncfestmisccost;

    @GridField(name = "ncftotalestcost")
    
    private BigDecimal ncftotalestcost;

    @GridField(name = "createdby")
    
    private String createdBy;

    @GridField(name = "created")
    
    private Date createdDate;

    @GridField(name = "updatedby")
    
    private String updatedBy;

    @Setter
    @GridField(name = "updated")
    
    private Date updatedDate;

    //Custom fields panel
    @Transient
    
    private CustomField[] customFields;

    public void setCustomFields(CustomField[] customFields) {
        this.customFields = customFields;
    }

}

