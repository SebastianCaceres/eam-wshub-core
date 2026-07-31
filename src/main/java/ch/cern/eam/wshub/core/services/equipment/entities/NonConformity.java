package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.annotations.GridField;
import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
@Getter
@Setter
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "R5NONCONFORMITIES")
public class NonConformity {
    @GridField(name = "description")
    @Column(name = "NCR_DESC")
    private String description;

    @GridField(name = "organization")
    private String organizationCode;

    @GridField(name = "nonconformity")
    @Id
    @Column(name = "NCR_CODE")
    private String code;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    @GridField(name = "aspectcode")
    
    private String aspectCode;

    @GridField(name = "aspectdescription")
    
    private String aspectDescription;

    @GridField(name = "classCode")
    
    private String classCode;

    @GridField(name = "classorg")
    
    private String classOrgCode;

    @GridField(name = "department")
    
    private String department;

    @GridField(name = "equipmentdesc")
    
    private String equipmentDesc;

    @GridField(name = "equipment")
    
    private String equipmentCode;

    @GridField(name = "equipmentorg")
    
    private String equipmentOrg;

    @GridField(name = "equipmentassignedto")
    
    private String equipmentAssignedTo;

    @GridField(name = "equipmentcategory")
    
    private String equipmentCategory;

    @GridField(name = "equipmentcriticality")
    
    private String equipmentCriticality;

    @GridField(name = "equipmentcriticalitydesc")
    
    private String equipmentCriticalityDesc;

    @GridField(name = "equipmentdepartment")
    
    private String equipmentDepartment;

    @GridField(name = "equipmentclass")
    
    private String equipmentClassCode;

    @GridField(name = "equipmentclassorg")
    
    private String equipmentClassOrg;

    @GridField(name = "equipmentstatusdesc")
    
    private String equipmentStatusDesc;

    @GridField(name = "equipmentstatus")
    
    private String equipmentStatus;

    @GridField(name = "equipmentmanufacturer")
    
    private String equipmentManufacturer;

    @GridField(name = "equipmentmodel")
    
    private String equipmentModel;

    @GridField(name = "equipmentoperationalstatusdesc")
    
    private String equipmentOperationalStatusDesc;

    @GridField(name = "equipmentoperationalstatus")
    
    private String equipmentOperationalStatus;

    @GridField(name = "equipmentsafety")
    
    private Boolean equipmentSafety;

    @GridField(name = "equipmenttypedesc")
    
    private String equipmenTtypeDesc;

    @GridField(name = "equipmenttype")
    
    private String equipmentType;

    @GridField(name = "highestobservation")
    
    private BigDecimal highestObservation;

    @GridField(name = "locationdesc")
    
    private String locationDesc;

    @GridField(name = "location")
    
    private String locationCode;

    @GridField(name = "locationorg")
    
    private String locationOrgCode;

    @GridField(name = "flow")
    
    private String flow;

    @GridField(name = "inspectiondirection")
    
    private String inspectionDirection;

    @GridField(name = "fromxcoordinate")
    
    private BigDecimal fromxCoordinate;

    @GridField(name = "fromycoordinate")
    
    private BigDecimal fromyCoordinate;

    @GridField(name = "ncffromgeoref")
    
    private String ncfFromGeoref;

    @GridField(name = "fromhorizontaloffset")
    
    private BigDecimal fromHorizontalOffset;

    @GridField(name = "fromhoroffsettype")
    
    private String fromoroffsettype;

    @GridField(name = "fromhoroffsetuom")
    
    private String fromhoroffsetuom;

    @GridField(name = "fromrelationshiptype")
    
    private String fromrelationshiptype;

    @GridField(name = "fromverticaloffset")
    
    private BigDecimal fromverticaloffset;

    @GridField(name = "fromverticaloffsettype")
    
    private String fromverticaloffsettype;

    @GridField(name = "fromverticaloffsetuom")
    
    private String fromverticaloffsetuom;

    @GridField(name = "fromlatitude")
    
    private BigDecimal fromlatitude;

    @GridField(name = "fromlongitude")
    
    private BigDecimal fromlongitude;

    @GridField(name = "fromoffset")
    
    private BigDecimal fromoffset;

    @GridField(name = "fromoffsetdirection")
    
    private String fromoffsetdirection;

    @GridField(name = "fromoffsetpercent")
    
    private BigDecimal fromoffsetpercent;

    @GridField(name = "ncffrompoint")
    
    private BigDecimal ncffrompoint;

    @GridField(name = "ncffromrefdesc")
    
    private String ncffromrefdesc;

    @GridField(name = "fromreferencepoint")
    
    private String fromreferencepoint;

    @GridField(name = "relatedfromreference")
    
    private String relatedfromreference;

    @GridField(name = "relationshiptype")
    
    private String relationshiptype;

    @GridField(name = "toxcoordinate")
    
    private BigDecimal toXcoordinate;

    @GridField(name = "toycoordinate")
    
    private BigDecimal toYcoordinate;

    @GridField(name = "ncftogeoref")
    
    private String ncfToGeoref;

    @GridField(name = "tohorizontaloffset")
    
    private BigDecimal toHorizontalOffset;

    @GridField(name = "tohorizontaloffsettype")
    
    private String toHorizontalOffseTtype;

    @GridField(name = "tohorizontaloffsetuom")
    
    private String toHorizontalOffsetUOM;

    @GridField(name = "torelationshiptype")
    
    private String toRelationshipType;

    @GridField(name = "toverticaloffset")
    
    private BigDecimal toVerticalOffset;

    @GridField(name = "toverticaloffsettype")
    
    private String toBerticalOffsetType;

    @GridField(name = "toverticaloffsetuom")
    
    private String toVerticalOffsetUOM;

    @GridField(name = "tolatitude")
    
    private BigDecimal toLatitude;

    @GridField(name = "tolongitude")
    
    private BigDecimal toLongitude;

    @GridField(name = "tooffset")
    
    private BigDecimal toOffset;

    @GridField(name = "tooffsetdirection")
    
    private String tOoffsetDirection;

    @GridField(name = "tooffsetpercent")
    
    private BigDecimal toOffsetPercent;

    @GridField(name = "ncftopoint")
    
    private BigDecimal ncfToPoint;

    @GridField(name = "ncftorefdesc")
    
    private String ncfTorefDesc;

    @GridField(name = "toreferencepoint")
    
    private String toReferencePoint;

    @GridField(name = "relatedtoreference")
    
    private String relatedToReference;

    @GridField(name = "ncftopointuom")
    
    private String ncfToPointUOM;

    @GridField(name = "ncffrompointuom")
    
    private String ncfFromPointUOM;

    @GridField(name = "materialtype")
    
    private String materialType;

    @GridField(name = "importance")
    
    private String importance;

    @GridField(name = "intensity")
    
    private String intensity;

    @GridField(name = "nonconformitynote")
    
    private String nonConformityNote;

    @GridField(name = "severity")
    
    private String severity;

    @GridField(name = "ncfsize")
    
    private BigDecimal ncfSize;

    @GridField(name = "typedesc")
    
    private String typeDesc;

    @GridField(name = "type")
    
    private String typeCode;

    @GridField(name = "typeorg")
    
    private String typeOrgCode;

    @GridField(name = "partdesc")
    
    private String partDesc;

    @GridField(name = "partorg")
    
    private String partorg;

    @GridField(name = "part")
    
    private String partCode;

    @GridField(name = "priority")
    
    private String priority;

    @GridField(name = "status")
    
    private String statusCode;

    @GridField(name = "conditionindex")
    
    private String conditionIndex;

    @GridField(name = "conditionscore")
    
    private BigDecimal conditionScore;

    @GridField(name = "createdby")
    
    private String createdBy;

    @GridField(name = "created")
    
    private Date createdDate;

    @Setter
    @GridField(name = "updated")
    
    private Date updatedDate;

    @GridField(name = "ncfestlaborcost")
    
    private BigDecimal ncfestlaborcost;

    @GridField(name = "ncfestmatlcost")
    
    private BigDecimal ncfestmatlcost;

    @GridField(name = "ncfestmisccost")
    
    private BigDecimal ncfestmisccost;

    @GridField(name = "ncftotalestcost")
    
    private BigDecimal ncftotalestcost;

    @Setter
    @GridField(name = "nextinspectdate")
    
    private Date nextInspectDate;

    @Setter
    @GridField(name = "nextinspectdateoverride")
    
    private Date nextInspectDateOverride;

    @GridField(name = "mergedinto")
    
    private String mergedinto;

    @GridField(name = "observationoverride")
    
    private Boolean observationoverride;

    @GridField(name = "repairdate")
    
    private Date repairDate;

    @GridField(name = "sourceobservation")
    
    private BigDecimal sourceobservation;

    @GridField(name = "updatedby")
    
    private String updatedBy;

    @GridField(name = "syslevel")
    
    private String syslevel;

    @GridField(name = "asslevel")
    
    private String asslevel;

    @GridField(name = "complevel")
    
    private String complevel;

    @GridField(name = "recordid")
    
    private BigInteger recordid;

    private UserDefinedFields userDefinedFields;

    @Transient
    
    private CustomField[] customFields;

    public Date getNextInspectDateOverride() {
        return nextInspectDateOverride;
    }

    public Date getNextInspectDate() {
        return nextInspectDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    @JsonProperty("customField")

    public CustomField[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomField[] customFields) {
        this.customFields = customFields;
    }

}
