package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartLotService;
import ch.cern.eam.wshub.core.services.material.entities.Lot;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.LotRepository;
import java.util.Optional;

public class PartLotServiceImpl implements PartLotService {

    private Tools tools;

    private ApplicationData applicationData;

    private LotRepository lotRepository;

    public PartLotServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public PartLotServiceImpl(ApplicationData applicationData, Tools tools, LotRepository lotRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.lotRepository = lotRepository;
    }

    @Override
    public String createLot(InforContext context, Lot lot) throws InforException {
        Lot saved = lotRepository.save(lot);
        return saved.getCode();
    }

    @Override
    public Lot readLot(InforContext context, String lotPk) throws InforException {
        return lotRepository.findById(lotPk).orElse(null);
    }

    @Override
    public String updateLot(InforContext context, Lot lot) throws InforException {
        Lot saved = lotRepository.save(lot);
        return saved.getCode();
    }

    @Override
    public String deleteLot(InforContext context, String lotCode) throws InforException {
        lotRepository.deleteById(lotCode);
        return lotCode;
    }
}
