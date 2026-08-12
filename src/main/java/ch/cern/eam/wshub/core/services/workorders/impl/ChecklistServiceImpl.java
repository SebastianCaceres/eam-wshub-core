package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.client.InforClient;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.FindingRepository;
import ch.cern.eam.wshub.core.services.entities.Pair;
import ch.cern.eam.wshub.core.services.entities.Signature;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestFilter;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestResult;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestRow;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.ChecklistService;
import ch.cern.eam.wshub.core.services.workorders.TaskPlanService;
import ch.cern.eam.wshub.core.services.workorders.entities.*;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrderActivityChecklistItem.CheckListType;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrderActivityChecklistItem.ReturnType;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.CacheKey;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.*;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isNotEmpty;
import static ch.cern.eam.wshub.core.tools.GridTools.*;

public class ChecklistServiceImpl implements ChecklistService {

    private static final String PERFORMED_BY_1 = "PB01";

    private static final String PERFORMED_BY_2 = "PB02";

    private static final String REVIEWED_BY_1 = "RB01";

    private final Tools tools;

    private final GridsService gridsService;

    private final TaskPlanService taskPlanService;

    private FindingRepository findingRepository;

    public ChecklistServiceImpl(ApplicationData applicationData, Tools tools) {
        this.tools = tools;
        this.gridsService = new GridsServiceImpl(applicationData, tools);
        this.taskPlanService = new TaskPlanServiceImpl(applicationData, tools);
    }

    public ChecklistServiceImpl(ApplicationData applicationData, Tools tools, FindingRepository findingRepository) {
        this(applicationData, tools);
        this.findingRepository = findingRepository;
    }

    @Override
    public WorkOrderActivityChecklistSignatureResult[] getSignatures(InforContext context, String workOrderCode, String activityCode, TaskPlan taskPlan) throws InforException {
        return null;
    }

    private void getResponsibilityDescriptions(InforContext context, WorkOrderActivityChecklistSignatureResult[] signatures) throws InforException {
        GridRequest gridRequest = new GridRequest("LVUSERRESPONSIBILITIES");
        gridRequest.setDataspyID("4297");
        gridRequest.getParams().put("param.rentity", "RESP");
        List<GridRequestFilter> filters = new LinkedList<>();
        for (WorkOrderActivityChecklistSignatureResult signatureResult : signatures) {
            String responsibilityCode = signatureResult.getResponsibilityCode();
            if (responsibilityCode != null) {
                filters.add(new GridRequestFilter("responsibility", responsibilityCode, "=", GridRequestFilter.JOINER.OR, false, false));
            }
        }
        if (filters.isEmpty())
            return;
        gridRequest.setGridRequestFilters(filters);
        Map<String, String> responsibilityToDescription = convertGridResultToMap("responsibility", "description", gridsService.executeQuery(context, gridRequest));
        Arrays.stream(signatures).forEach(signature -> {
            if (responsibilityToDescription.containsKey(signature.getResponsibilityCode()))
                signature.setResponsibilityDescription(responsibilityToDescription.get(signature.getResponsibilityCode()));
        });
    }

    private WorkOrderActivityChecklistSignatureResult[] filterSignatures(WorkOrderActivityChecklistDefaultResult workOrderActivityCheckList, TaskPlan taskPlan) {
        String reviewerQualification = workOrderActivityCheckList.getReviewerQualification();
        String performer1Qualification = workOrderActivityCheckList.getPerformer1Qualification();
        String performer2Qualification = workOrderActivityCheckList.getPerformer2Qualification();
        String viewerQualification = taskPlan.getViewOnlyResponsibility();
        List<String> qualifications = new ArrayList<>();
        if (workOrderActivityCheckList.getUserQualifications() != null) {
            qualifications = workOrderActivityCheckList.getUserQualifications().stream().map(UserQualification::getUserDefinedCode).collect(Collectors.toList());
        }
        boolean noRequiredQualifications = reviewerQualification == null && performer1Qualification == null && performer2Qualification == null && viewerQualification == null;
        boolean isViewer = noRequiredQualifications || viewerQualification != null && qualifications.contains(viewerQualification);
        boolean isPerformer1 = noRequiredQualifications || performer1Qualification == null && isViewer || qualifications.contains(performer1Qualification);
        boolean isPerformer2 = noRequiredQualifications || performer2Qualification == null && isPerformer1 || qualifications.contains(performer2Qualification);
        boolean isReviewer = noRequiredQualifications || reviewerQualification == null && (isPerformer1 || isPerformer2) || qualifications.contains(reviewerQualification);
        List<WorkOrderActivityChecklistSignatureResult> signatures = new LinkedList<>();
        if (isPerformer1 || isReviewer || isViewer) {
            WorkOrderActivityChecklistSignatureResult perf1 = new WorkOrderActivityChecklistSignatureResult();
            perf1.setType(ChecklistServiceImpl.PERFORMED_BY_1);
            perf1.setSigner(workOrderActivityCheckList.getPerformer1Name());
            perf1.setViewAsViewer(isViewer);
            perf1.setViewAsPerformer(isPerformer1);
            perf1.setViewAsReviewer(isReviewer);
            perf1.setTime(workOrderActivityCheckList.getTimePerf1() != null ? workOrderActivityCheckList.getTimePerf1() : null);
            perf1.setResponsibilityCode(performer1Qualification);
            signatures.add(perf1);
        }
        // TODO: refactor the check below to move this CERN-specific logic to EAM Light
        boolean performedBy2Hidden = taskPlan.getUserDefinedFields().getUdfchkbox02();
        if (!performedBy2Hidden && (isPerformer2 || isReviewer || isViewer)) {
            WorkOrderActivityChecklistSignatureResult perf2 = new WorkOrderActivityChecklistSignatureResult();
            perf2.setType(PERFORMED_BY_2);
            perf2.setSigner(workOrderActivityCheckList.getPerformer2Name());
            perf2.setViewAsViewer(isViewer);
            perf2.setViewAsPerformer(isPerformer2);
            perf2.setViewAsReviewer(isReviewer);
            perf2.setTime(workOrderActivityCheckList.getTimePerf2() != null ? workOrderActivityCheckList.getTimePerf2() : null);
            perf2.setResponsibilityCode(performer2Qualification);
            signatures.add(perf2);
        }
        if (isPerformer1 || isPerformer2 || isReviewer || isViewer) {
            WorkOrderActivityChecklistSignatureResult reviewer = new WorkOrderActivityChecklistSignatureResult();
            reviewer.setType(REVIEWED_BY_1);
            reviewer.setSigner(workOrderActivityCheckList.getReviewerName());
            reviewer.setViewAsViewer(isViewer);
            reviewer.setViewAsPerformer(isReviewer);
            reviewer.setViewAsReviewer(isReviewer);
            reviewer.setTime(workOrderActivityCheckList.getTimeRev1() != null ? workOrderActivityCheckList.getTimeRev1() : null);
            if (reviewerQualification == null && performer1Qualification != null && performer2Qualification == null)
                reviewerQualification = performer1Qualification;
            reviewer.setResponsibilityCode(reviewerQualification);
            signatures.add(reviewer);
        }
        return signatures.toArray(new WorkOrderActivityChecklistSignatureResult[0]);
    }

    @Override
    public WorkOrderActivityChecklistSignatureResponse eSignWorkOrderActivityChecklist(InforContext context, WorkOrderActivityChecklistSignature workOrderActivityCheckListSignature) throws InforException {
        return null;
    }

    @Override
    public String updateWorkOrderActivityCheckList(InforContext context, WorkOrderActivityChecklist workOrderActivityChecklist, boolean shouldMergeExistingValues) throws InforException {
        return null;
    }

    @Override
    public String updateWorkOrderChecklistItem(InforContext context, WorkOrderActivityChecklistItem workOrderActivityChecklistItem, TaskPlan taskPlan) throws InforException {
        return null;
        //
        // Fetch it first
        //
        // Sync afterwards
    }

    @Override
    public String createTaskplanChecklist(InforContext context, TaskplanCheckList taskChecklist) throws InforException {
        return null;
        //
        // TASK LIST ID
        //
        // DESCRIPTION
        //
        // SEQUENCE
        //
        // TYPE
        //
        // REQUIRED ENTRY
        //
        // EQUIPMENT LEVEL
        //
        // POSSIBLE FINDINGS
        //
        // UOM
        //
        // ASPECT ID
        //
        // POINT TYPE ID
        //
        // REPEATING OCCURRENCES
        //
        // FOLLOW-UP TASK PLAN
        //
        // CLASS
        //
        // CATEGORY
        //
        // EQUIPMENT FILTER
    }

    @Override
    public WorkOrderActivityChecklistItem[] readWorkOrderChecklistItems(InforContext context, Activity activity) throws InforException {
        // Fetch the data
        GridRequest gridRequest = new GridRequest("WSJOBS_ACK");
        gridRequest.setRowCount(2000);
        gridRequest.setUseNative(true);
        gridRequest.setUserFunctionName("WSJOBS");
        gridRequest.getParams().put("param.workordernum", activity.getWorkOrderNumber());
        gridRequest.getParams().put("param.activity", activity.getActivityCode().toString());
        gridRequest.getParams().put("param.jobseq", "0");
        GridRequestResult gridRequestResult = gridsService.executeQuery(context, gridRequest);
        LinkedList<WorkOrderActivityChecklistItem> checklists = new LinkedList<>();
        for (GridRequestRow row : gridRequestResult.getRows()) {
            checklists.add(getCheckList(context, row, activity));
        }
        return checklists.toArray(new WorkOrderActivityChecklistItem[] {});
    }

    private WorkOrderActivityChecklistItem getCheckList(InforContext context, GridRequestRow row, Activity activity) throws InforException {
        WorkOrderActivityChecklistItem checklist = new WorkOrderActivityChecklistItem();
        checklist.setWorkOrderCode(activity.getWorkOrderNumber());
        checklist.setActivityCode(activity.getActivityCode().toString());
        checklist.setCheckListCode(getCellContent("checklistcode", row));
        //checklistTemp.setOccurrence(v_result.getString("ack_occurrence"));
        checklist.setSequence(getCellContent("checklistsequence", row));
        checklist.setEquipmentCode(getCellContent("equipment", row));
        checklist.setEquipmentDesc(getCellContent("equipmentdesc", row));
        checklist.setType(getCellContent("checklisttype", row));
        checklist.setColor(getCellContent("color", row));
        // FOLLOW-UP
        checklist.setFollowUp(decodeBoolean(getCellContent("followup", row)));
        // FOLLOW-UP WORK ORDER
        String followUpWorkOrderActivity = getCellContent("followupwoactivity", row);
        if (isNotEmpty(followUpWorkOrderActivity)) {
            // Remove the activity after the Work Order Number
            checklist.setFollowUpWorkOrder(followUpWorkOrderActivity.split("-")[0]);
        }
        // REQUIRED
        String required = getCellContent("requiredtoclosedocument", row);
        checklist.setRequiredToClose("Yes".equalsIgnoreCase(required));
        checklist.setConditional("X".equalsIgnoreCase(getCellContent("conditionpk", row)));
        // NOTES
        checklist.setNotes(getCellContent("notes", row));
        //checklistTemp.setFinalOccurrence(v_result.getString("ack_finaloccurrence"));
        checklist.setDesc(getCellContent("checklistdescription", row));
        checklist.setHideFollowUp(cellEquals(row, "hidefollowup", "true"));
        checklist.setMinimumValue(encodeBigInteger(getCellContent("minimumslidervalue", row), "minimumslidervalue"));
        checklist.setMaximumValue(encodeBigInteger(getCellContent("maximumslidervalue", row), "maximumslidervalue"));
        checklist.setMinimumValue2(encodeBigInteger(getCellContent("minimumslidervalue2", row), "minimumslidervalue2"));
        checklist.setMaximumValue2(encodeBigInteger(getCellContent("maximumslidervalue2", row), "maximumslidervalue2"));
        checklist.setNotApplicableOption(getCellContent("notapplicable", row));
        checklist.setChecklistDefinitionCode(getCellContent("taskchecklistcode", row));
        //
        // VALUES FOR DIFFERENT CHECKLIST TYPES
        //
        switch(checklist.getType()) {
            case CheckListType.CHECKLIST_ITEM:
                if (cellEquals(row, "completed", "true")) {
                    checklist.setResult(ReturnType.COMPLETED);
                } else {
                    checklist.setResult(ReturnType.NULL);
                }
                break;
            case CheckListType.QUESTION_YES_NO:
                if (cellEquals(row, "yes", "true")) {
                    checklist.setResult(ReturnType.YES);
                } else if (cellEquals(row, "no", "true")) {
                    checklist.setResult(ReturnType.NO);
                } else {
                    checklist.setResult(null);
                }
                break;
            case CheckListType.QUALITATIVE:
                checklist.setFinding(getCellContent("finding", row));
                checklist.setPossibleFindings(getPossibleFindings(context, row));
                break;
            case CheckListType.INSPECTION:
                checklist.setFinding(getCellContent("finding", row));
                checklist.setPossibleFindings(getPossibleFindings(context, row));
            // no break here, INSPECTION is the same as QUANTITATIVE/METER_READING,
            // but with findings and possible findings, so we will set the numeric value and UOM below
            case CheckListType.QUANTITATIVE:
            case CheckListType.METER_READING:
                checklist.setNumericValue(encodeBigDecimal(getCellContent("value", row), ""));
                checklist.setUOM(getCellContent("uom", row));
                // this is set for backward compatibility reasons, deprecated, do not use in new applications
                // TODO: update all applications to use the numeric value and remove this
                checklist.setResult(getCellContent("value", row));
                break;
            case CheckListType.GOOD_POOR:
                if (cellEquals(row, "good", "true")) {
                    checklist.setResult(ReturnType.GOOD);
                } else if (cellEquals(row, "poor", "true")) {
                    checklist.setResult(ReturnType.POOR);
                } else {
                    checklist.setResult(ReturnType.NULL);
                }
                break;
            case CheckListType.NONCONFORMITY_MEASUREMENT:
                checklist.setNumericValue(encodeBigDecimal(getCellContent("value", row), ""));
                checklist.setUOM(getCellContent("uom", row));
            // no break here, NONCONFORMITY_MEASUREMENT is the same as NONCONFORMITY_CHECK,
            // but with a numeric value and UOM, so we will set the result to OK/NONCONFORMITY below
            case CheckListType.NONCONFORMITY_CHECK:
                if (cellEquals(row, "ok", "true")) {
                    checklist.setResult(ReturnType.OK);
                } else if (cellEquals(row, "nonconformityfound", "true")) {
                    checklist.setResult(ReturnType.NONCONFORMITY);
                } else {
                    checklist.setResult(ReturnType.NULL);
                }
                break;
            case CheckListType.OK_ADJUSTED_MEASUREMENT:
                checklist.setNumericValue(encodeBigDecimal(getCellContent("value", row), ""));
                checklist.setUOM(getCellContent("uom", row));
            // no break here, OK_ADJUSTED_MEASUREMENT is the same as OK_ADJUSTED,
            // but with a numeric value and UOM, so we will set the result to OK/ADJUSTED below
            case CheckListType.OK_ADJUSTED:
                if (cellEquals(row, "ok", "true")) {
                    checklist.setResult(ReturnType.OK);
                } else if (cellEquals(row, "adjusted", "true")) {
                    checklist.setResult(ReturnType.ADJUSTED);
                } else {
                    checklist.setResult(ReturnType.NULL);
                }
                break;
            case CheckListType.OK_REPAIR_NEEDED:
                checklist.setFinding(getCellContent("resolution", row));
                if (cellEquals(row, "ok", "true")) {
                    checklist.setResult(ReturnType.OK);
                } else if (cellEquals(row, "repairsneeded", "true")) {
                    checklist.setResult(ReturnType.REPAIRSNEEDED);
                } else {
                    checklist.setResult(ReturnType.NULL);
                }
                break;
            case CheckListType.DATE:
                checklist.setDate(convertStringToDate(getCellContent("checklistdate", row)));
                break;
            case CheckListType.DATETIME:
                checklist.setDateTime(convertStringToDate(getCellContent("checklistdatetime", row)));
                break;
            case CheckListType.FREE_TEXT:
                checklist.setFreeText(getCellContent("checklistfreetext", row));
                break;
            case CheckListType.ENTITY:
                checklist.setEntityCode(getCellContent("entitycode", row));
                checklist.setEntityCodeOrg(getCellContent("entitycodeorg", row));
                checklist.setEntityType(getCellContent("rentitycode", row));
                checklist.setEntityClass(getCellContent("entityclassoptions", row));
                break;
            case CheckListType.DUAL_QUANTITATIVE:
                checklist.setNumericValue(encodeBigDecimal(getCellContent("value", row), ""));
                checklist.setNumericValue2(encodeBigDecimal(getCellContent("value2", row), ""));
                checklist.setUOM(getCellContent("uom", row));
                checklist.setUOM2(getCellContent("uom2", row));
                break;
        }
        return checklist;
    }

    /**
     * Fetches the list of Findings (code, desc) for GridRequestRow containing comma-delimited string of finding codes.
     *
     * @param context
     * @param row
     * @return
     */
    private List<Finding> getPossibleFindings(InforContext context, GridRequestRow row) {
        List<String> possibleFindings = Arrays.asList(getCellContent("possiblefindings", row).split(","));
        return possibleFindings.stream().map(findingCode -> {
            String findingsCacheKey = Tools.getCacheKey(context, findingCode);
            Function<String, String> loader = key -> loadFinding(context, findingCode);
            String finding = Optional.ofNullable(InforClient.cacheMap.get(CacheKey.FINDINGS)).map(cache -> (String) cache.get(findingsCacheKey, loader)).orElseGet(() -> loader.apply(findingsCacheKey));
            return new Finding(findingCode, finding != null ? finding : findingCode);
        }).collect(Collectors.toList());
    }

    private String loadFinding(InforContext context, String findingCode) {
        try {
            if (findingRepository != null) {
                Finding finding = findingRepository.findById(findingCode).orElse(null);
                if (finding != null) {
                    return finding.getDesc();
                }
            }
            GridRequest gridRequest = new GridRequest("ISFIND", GridRequest.GRIDTYPE.LIST);
            gridRequest.addFilter("findingcode", findingCode, "=");
            return extractSingleResult(gridsService.executeQuery(context, gridRequest), "findingdesc");
        } catch (Exception e) {
            tools.log(Level.WARNING, "Finding could not be fetched: " + e.getMessage());
            return null;
        }
    }

    private boolean cellEquals(GridRequestRow row, String key, String value) {
        return getCellContent(key, row) != null && getCellContent(key, row).equals(value);
    }

    private String getValue(ResultSet v_result) throws SQLException {
        double value = v_result.getDouble("ack_value");
        if (v_result.wasNull()) {
            return null;
        } else {
            return Double.toString(value);
        }
    }

    /**
     * Webservice to create Follow Up workorders for checklist activities
     * @param context
     * @param activity
     * @return Number of work orders that were created
     * @throws InforException
     */
    @Override
    public Long createFollowUpWorkOrders(InforContext context, Activity activity) throws InforException {
        return null;
    }

    @Override
    public WorkOrderActivityChecklistDefinition getChecklistDefinition(InforContext context, TaskPlan taskPlan, String code) throws InforException {
        GridRequest gridRequest = new GridRequest("WSTASK_TCH", 1);
        gridRequest.addParam("param.task", taskPlan.getCode());
        gridRequest.addParam("param.revision", taskPlan.getTaskRevision() == null ? null : taskPlan.getTaskRevision().toString());
        gridRequest.addFilter("checklistitem", code, "EQUALS");
        GridRequestResult result = gridsService.executeQuery(context, gridRequest);
        WorkOrderActivityChecklistDefinition definition = convertGridResultToObject(WorkOrderActivityChecklistDefinition.class, null, result).stream().findFirst().orElse(null);
        String notApplicableOptionsString = extractSingleResult(result, "naoptions");
        // optimization to not call the grid request below
        if (notApplicableOptionsString == null || notApplicableOptionsString.isEmpty()) {
            return definition;
        }
        GridRequest notApplicableOptionsRequest = new GridRequest("LVNAOPTIONS", 2000);
        Map<String, String> notApplicableOptionsMap = convertGridResultToMap("code", "description", gridsService.executeQuery(context, notApplicableOptionsRequest));
        List<Pair> notApplicableOptions = Arrays.stream(notApplicableOptionsString.split(",")).map(optionCode -> new Pair(optionCode, notApplicableOptionsMap.get(optionCode))).collect(Collectors.toList());
        definition.setNotApplicableOptions(notApplicableOptions);
        return definition;
    }
}
