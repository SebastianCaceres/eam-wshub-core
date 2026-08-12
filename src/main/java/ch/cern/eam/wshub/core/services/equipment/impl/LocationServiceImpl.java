package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.equipment.LocationService;
import ch.cern.eam.wshub.core.services.equipment.entities.Location;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.LocationRepository;
import java.util.List;
import java.util.Optional;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class LocationServiceImpl implements LocationService {

    private Tools tools;

    private ApplicationData applicationData;

    private LocationRepository locationRepository;

    public LocationServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public LocationServiceImpl(ApplicationData applicationData, Tools tools, LocationRepository locationRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.locationRepository = locationRepository;
    }

    //
    // BATCH WEB SERVICES
    //
    public BatchResponse<String> createLocationBatch(InforContext context, List<Location> locations) {
        return tools.batchOperation(context, this::createLocation, locations);
    }

    public BatchResponse<Location> readLocationBatch(InforContext context, List<String> locationCodes) {
        return tools.batchOperation(context, this::readLocation, locationCodes);
    }

    public BatchResponse<String> updateLocationBatch(InforContext context, List<Location> locations) {
        return tools.batchOperation(context, this::updateLocation, locations);
    }

    public BatchResponse<String> deleteLocationBatch(InforContext context, List<String> locationCodes) {
        return tools.batchOperation(context, this::deleteLocation, locationCodes);
    }

    public Location readLocation(InforContext context, String locationCode) throws InforException {
        return locationRepository.findById(locationCode).orElse(null);
    }

    public String createLocation(InforContext context, Location locationParam) throws InforException {
        Location saved = locationRepository.save(locationParam);
        return saved.getCode();
    }

    public String updateLocation(InforContext context, Location locationParam) throws InforException {
        Location saved = locationRepository.save(locationParam);
        return saved.getCode();
    }

    @Override
    public String deleteLocation(InforContext context, String locationCode) throws InforException {
        locationRepository.deleteById(locationCode);
        return locationCode;
    }
}
