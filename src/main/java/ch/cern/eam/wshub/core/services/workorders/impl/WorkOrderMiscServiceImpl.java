package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartService;
import ch.cern.eam.wshub.core.services.material.entities.MaterialList;
import ch.cern.eam.wshub.core.services.material.impl.PartServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.WorkOrderMiscService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.workorders.entities.RouteEquipment;
import ch.cern.eam.wshub.core.services.workorders.entities.TaskPlan;
import ch.cern.eam.wshub.core.services.workorders.entities.AdditionalWOEquipDetails;
import java.math.BigDecimal;
import java.math.BigInteger;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isNotEmpty;

public class WorkOrderMiscServiceImpl implements WorkOrderMiscService {

    private Tools tools;

    private ApplicationData applicationData;

    private PartService partService;

    public WorkOrderMiscServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String createMeterReading(InforContext context, ch.cern.eam.wshub.core.services.workorders.entities.MeterReading meterReadingParam) throws InforException {
        return null;
    }

    public String createWorkOrderAdditionalCost(InforContext context, ch.cern.eam.wshub.core.services.workorders.entities.WorkOrderAdditionalCosts workOrderAddCostsParam) throws InforException {
        return null;
    }

    public String createMaterialList(InforContext context, MaterialList materialList) throws InforException {
        return null;
    }

    public String addWorkOrderPart(InforContext context, ch.cern.eam.wshub.core.services.entities.WorkOrderPart workOrderPart) throws InforException {
        return null;
    }

    public String createRouteEquipment(InforContext context, RouteEquipment routeEquipment) throws InforException {
        return null;
        //
        // ROUTE ID
        //
        // EQUIPMENT ID
    }

    public String deleteRouteEquipment(InforContext context, RouteEquipment routeEquipment) throws InforException {
        return null;
    }

    //TODO
    /*
	public String syncRoutes(String nothing, Credentials credentials, String sessionID) throws InforException {
		EntityManager em = tools.getEntityManager();
		try {
			List<RouteEquipment> equipmentUdfRoutes = em.createNamedQuery("FINDUDFROUTES", RouteEquipment.class)
					.getResultList();
			Map<String, List<RouteEquipment>> equipmentUdfRoutesMap = equipmentUdfRoutes.stream()
					.collect(Collectors.groupingBy(RouteEquipment::getRouteCode));

			for (String route : equipmentUdfRoutesMap.keySet()) {
				List<RouteEquipment> equipmentForRoute = em.createNamedQuery("FINDEQPROUTES", RouteEquipment.class)
						.setParameter("route", route).getResultList();
				int sequenceOfLastRoute = 1;
				if (equipmentForRoute.size() > 0) {
					sequenceOfLastRoute = Integer.parseInt(
							equipmentForRoute.get(equipmentForRoute.size() - 1).getRouteEquipmentSequence()) + 5;
				}
				for (RouteEquipment re : equipmentUdfRoutesMap.get(route)) {
					re.setRouteEquipmentSequence(Integer.toString(sequenceOfLastRoute));
					System.out.println("CREATING: " + re);
					createRouteEquipment(re, credentials, sessionID);
					sequenceOfLastRoute += 5;
				}
			}

			return "OK";
		} catch (Exception e) {
			throw tools.generateFault("Couldn't fetch activities for this work order. " + e.getMessage());
		} finally {
			em.clear();
			em.close();
		}


	}
	*/
    public String createTaskPlan(InforContext context, TaskPlan taskPlan) throws InforException {
        return null;
    }

    @Override
    public AdditionalWOEquipDetails getEquipLinearDetails(final InforContext context, final String eqCode) throws InforException {
        return null;
    }
}
