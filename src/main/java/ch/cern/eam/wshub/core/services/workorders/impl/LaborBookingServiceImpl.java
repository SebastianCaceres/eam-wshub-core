package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.ActivityRepository;
import ch.cern.eam.wshub.core.repositories.LaborBookingRepository;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.ChecklistService;
import ch.cern.eam.wshub.core.services.workorders.LaborBookingService;
import ch.cern.eam.wshub.core.services.workorders.TaskPlanService;
import ch.cern.eam.wshub.core.services.workorders.entities.*;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.GridTools;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_fields.*;
import net.datastream.schemas.mp_functions.mp0035_001.MP0035_GetActivity_001;
import net.datastream.schemas.mp_functions.mp0037_001.MP0037_AddActivity_001;
import net.datastream.schemas.mp_functions.mp0038_001.MP0038_SyncActivity_001;
import net.datastream.schemas.mp_functions.mp0039_001.MP0039_DeleteActivity_001;
import net.datastream.schemas.mp_functions.mp0042_001.MP0042_AddLaborBooking_001;
import net.datastream.schemas.mp_results.mp0035_001.MP0035_GetActivity_001_Result;
import net.datastream.schemas.mp_results.mp0037_001.MP0037_AddActivity_001_Result;
import net.datastream.schemas.mp_results.mp0038_001.MP0038_SyncActivity_001_Result;
import net.datastream.schemas.mp_results.mp0039_001.MP0039_DeleteActivity_001_Result;
import net.datastream.schemas.mp_results.mp0042_001.MP0042_AddLaborBooking_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isNotEmpty;

public class LaborBookingServiceImpl implements LaborBookingService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private ChecklistService checklistService;

    private GridsService gridsService;

    private TaskPlanService taskPlanService;

    private LaborBookingRepository laborBookingRepository;

    private ActivityRepository activityRepository;

    public LaborBookingServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null, null);
    }

    public LaborBookingServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, LaborBookingRepository laborBookingRepository, ActivityRepository activityRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.laborBookingRepository = laborBookingRepository;
        this.activityRepository = activityRepository;
        this.checklistService = new ChecklistServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
        this.gridsService = new GridsServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
        this.taskPlanService = new TaskPlanServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
    }

    public List<LaborBooking> readLaborBookings(InforContext context, String workOrderNumber) throws InforException {
        if (laborBookingRepository != null) {
            List<LaborBooking> laborBookings = laborBookingRepository.findByWorkOrder(workOrderNumber);
            if (laborBookings != null) {
                return laborBookings;
            }
        }
        GridRequest gridRequest = new GridRequest("WSJOBS_BOO");
        gridRequest.setUserFunctionName("WSJOBS");
        gridRequest.getParams().put("param.jobnum", workOrderNumber);
        gridRequest.getParams().put("param.headeractivity", "0");
        gridRequest.getParams().put("param.headerjob", "0");
        gridRequest.setRowCount(500);
        return GridTools.convertGridResultToObject(LaborBooking.class, null, gridsService.executeQuery(context, gridRequest));
    }

    public String createLaborBooking(InforContext context, LaborBooking laborBookingParam) throws InforException {
        LaborBooking saved = laborBookingRepository.save(laborBookingParam);
        return saved.getCode() != null ? saved.getCode() : laborBookingParam.getActivityCode();
        //
        // CALL THE WS
    }

    public Activity[] readActivities(InforContext context, String workOrderNumber, Boolean includeChecklists) throws InforException {
        try {
            List<Activity> activities;
            if (activityRepository != null) {
                activities = activityRepository.findByWorkOrder(workOrderNumber);
            } else {
                GridRequest gridRequest = new GridRequest("WSJOBS_ACT");
                gridRequest.setRowCount(1000);
                gridRequest.setUserFunctionName("WSJOBS");
                gridRequest.getParams().put("param.jobnum", workOrderNumber);
                activities = tools.getGridTools().convertGridResultToObject(Activity.class, null, gridsService.executeQuery(context, gridRequest));
            }
            if (includeChecklists) {
                // Read checklists for all activities in parallel
                List<Runnable> runnables = activities.stream().<Runnable>map(activity -> () -> {
                    try {
                        TaskPlan taskPlan = new TaskPlan();
                        taskPlan.setCode(activity.getTaskCode());
                        taskPlan = taskPlanService.getTaskPlan(context, taskPlan);
                        WorkOrderActivityChecklistSignatureResult[] signatures = checklistService.getSignatures(context, workOrderNumber, activity.getActivityCode().toString(), taskPlan);
                        if (signatures.length > 0) {
                            activity.setChecklists(checklistService.readWorkOrderChecklistItems(context, activity));
                            if (taskPlan.getReviewedByRequired()) {
                                activity.setSignatures(Arrays.stream(signatures).collect(Collectors.toMap(WorkOrderActivityChecklistSignatureResult::getType, Function.identity())));
                            } else if (taskPlan.getPerformedByRequired()) {
                                activity.setSignatures(Arrays.stream(signatures).filter(signature -> !signature.getType().equals("RB01")).collect(Collectors.toMap(WorkOrderActivityChecklistSignatureResult::getType, Function.identity())));
                            }
                            activity.setForceActivityExpansion(taskPlan.getUserDefinedFields().getUdfchkbox03());
                        } else {
                            activity.setChecklists(new WorkOrderActivityChecklistItem[0]);
                        }
                    } catch (Exception e) {
                        activity.setChecklists(new WorkOrderActivityChecklistItem[0]);
                    }
                }).collect(Collectors.toList());
                tools.processRunnables(runnables);
            }
            //
            return activities.stream().toArray(Activity[]::new);
        } catch (Exception e) {
            throw tools.generateFault("Couldn't fetch activities for this work order: " + e.getMessage());
        }
    }

    public String createActivity(InforContext context, Activity activityParam) throws InforException {
        Activity saved = activityRepository.save(activityParam);
        return saved.getActivityCode() != null ? saved.getActivityCode() + "" : "";
        // CALL THE WS
    }

    public String updateActivity(InforContext context, Activity activityParam) throws InforException {
        return updateActivity(context, activityParam, null);
    }

    public String updateActivity(InforContext context, Activity activityParam, String confirmDeleteChecklist) throws InforException {
        Activity saved = activityRepository.save(activityParam);
        return saved.getActivityCode() != null ? saved.getActivityCode() + "" : "";
        //
        // READ THE ACTIVITY FIRST
        //
        // CALL THE WS
    }

    public String deleteActivity(InforContext context, Activity activityParam) throws InforException {
        activityRepository.deleteById(activityParam.getActivityCode());
        return activityParam.getActivityCode() != null ? activityParam.getActivityCode() + "" : "";
        //
        // CALL THE WS
    }
}
