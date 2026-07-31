package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.TaskPlanService;
import ch.cern.eam.wshub.core.services.workorders.entities.TaskPlan;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.TaskPlanRepository;
import net.datastream.schemas.mp_functions.mp0079_001.MP0079_GetTask_001;
import net.datastream.schemas.mp_results.mp0079_001.MP0079_GetTask_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;

import java.math.BigInteger;

public class TaskPlanServiceImpl implements TaskPlanService {
    private Tools tools;
    private InforWebServicesPT inforws;
    private ApplicationData applicationData;
    private TaskPlanRepository taskPlanRepository;

    public TaskPlanServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public TaskPlanServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, TaskPlanRepository taskPlanRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.taskPlanRepository = taskPlanRepository;
    }

    @Override
    public TaskPlan getTaskPlan(InforContext context, TaskPlan taskPlan) throws InforException {
        if (taskPlanRepository != null && taskPlan != null && taskPlan.getCode() != null) {
            TaskPlan found = taskPlanRepository.findById(taskPlan.getCode()).orElse(null);
            if (found != null) {
                return found;
            }
        }

        MP0079_GetTask_001 getTaskPlan = new MP0079_GetTask_001();
        if(taskPlan.getTaskRevision() == null) {
            taskPlan.setTaskRevision(new BigInteger("0"));
        }
        tools.getInforFieldTools().transformWSHubObject(getTaskPlan, taskPlan, context);
        MP0079_GetTask_001_Result getTask_001_result =
                tools.performInforOperation(context, inforws::getTaskOp, getTaskPlan);

        TaskPlan res = new TaskPlan();
        tools.getInforFieldTools().transformInforObject(res, getTask_001_result.getResultData().getTask(), context);
        return res;
    }
}
