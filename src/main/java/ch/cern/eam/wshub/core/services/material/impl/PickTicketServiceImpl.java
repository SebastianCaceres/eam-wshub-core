package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PickTicketService;
import ch.cern.eam.wshub.core.services.material.entities.PickTicket;
import ch.cern.eam.wshub.core.services.material.entities.PickTicketPart;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import org.openapplications.oagis_segments.QUANTITY;
import javax.xml.ws.Holder;
import java.math.BigDecimal;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;
import ch.cern.eam.wshub.core.repositories.PickTicketRepository;
import java.util.Optional;

public class PickTicketServiceImpl implements PickTicketService {

    private Tools tools;

    private ApplicationData applicationData;

    private PickTicketRepository pickTicketRepository;

    public PickTicketServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public PickTicketServiceImpl(ApplicationData applicationData, Tools tools, PickTicketRepository pickTicketRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.pickTicketRepository = pickTicketRepository;
    }

    public String createPickTicket(InforContext context, PickTicket pickTicketParam) throws InforException {
        PickTicket saved = pickTicketRepository.save(pickTicketParam);
        return saved.getCode();
    }

    public String updatePickTicket(InforContext context, PickTicket pickTicketParam) throws InforException {
        PickTicket saved = pickTicketRepository.save(pickTicketParam);
        return saved.getCode();
    }


    public PickTicket readPickTicket(InforContext context, String code) throws InforException {
        return pickTicketRepository.findById(code).orElse(null);
    }

    public String addPartToPickTicket(InforContext context, PickTicketPart pickTicketPartParam) throws InforException {
        return null;
    }
}
