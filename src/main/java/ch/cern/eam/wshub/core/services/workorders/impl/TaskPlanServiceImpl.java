package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.TaskPlanService;
import ch.cern.eam.wshub.core.services.workorders.entities.TaskPlan;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.TaskPlanRepository;
import java.math.BigInteger;

public class TaskPlanServiceImpl implements TaskPlanService {

    private Tools tools;

    private ApplicationData applicationData;

    private TaskPlanRepository taskPlanRepository;

    public TaskPlanServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public TaskPlanServiceImpl(ApplicationData applicationData, Tools tools, TaskPlanRepository taskPlanRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.taskPlanRepository = taskPlanRepository;
    }

    @Override
    public TaskPlan getTaskPlan(InforContext context, TaskPlan taskPlan) throws InforException {
        return taskPlanRepository.findById(taskPlan.getCode()).orElse(null);
    }
}
