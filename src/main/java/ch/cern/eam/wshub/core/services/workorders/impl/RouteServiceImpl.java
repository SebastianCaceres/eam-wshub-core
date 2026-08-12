package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.entities.Route;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.RouteEquipmentRepository;
import ch.cern.eam.wshub.core.repositories.RouteRepository;
import ch.cern.eam.wshub.core.services.workorders.RouteService;

public class RouteServiceImpl implements RouteService {

    private Tools tools;

    private ApplicationData applicationData;

    private RouteEquipmentRepository routeEquipmentRepository;

    private RouteRepository routeRepository;

    public RouteServiceImpl(ApplicationData applicationData, Tools tools, RouteEquipmentRepository routeEquipmentRepository) {
    }

    public RouteServiceImpl(ApplicationData applicationData, Tools tools, RouteEquipmentRepository routeEquipmentRepository, RouteRepository routeRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.routeEquipmentRepository = routeEquipmentRepository;
        this.routeRepository = routeRepository;
    }

    public Route readRoute(InforContext inforContext, String routeCode) throws InforException {
        return routeRepository.findById(routeCode).orElse(null);
    }

    public String createRoute(InforContext inforContext, Route route) throws InforException {
        Route saved = routeRepository.save(route);
        return saved.getCode();
    }
}
