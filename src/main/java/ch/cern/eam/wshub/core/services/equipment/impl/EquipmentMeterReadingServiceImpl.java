package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentMeterReadingService;
import ch.cern.eam.wshub.core.services.equipment.entities.EqpMeterReading;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;

public class EquipmentMeterReadingServiceImpl implements EquipmentMeterReadingService {

    private ApplicationData applicationData;

    private Tools tools;

    public EquipmentMeterReadingServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String createEquipmentMeterReading(final InforContext context, final EqpMeterReading eqpMeterReading, boolean rolloverAllowed) throws InforException {
        return null;
    }

    @Override
    public EqpMeterReading readEquipmentMeterReading(final InforContext context, String readingCode) throws InforException {
        return null;
    }

    @Override
    public String updateEquipmentMeterReading(final InforContext context, final EqpMeterReading eqpMeterReading) throws InforException {
        return null;
    }

    @Override
    public String deleteEquipmentMeterReading(final InforContext context, final String readingCode) throws InforException {
        return null;
    }

    @Override
    public EqpMeterReading readEquipmentMeterReadingDefault(final InforContext context, final EqpMeterReading eqpMeterReading) throws InforException {
        return null;
    }
}
