package ch.cern.eam.wshub.core.services.documents.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.documents.DocumentsService;
import ch.cern.eam.wshub.core.services.documents.entities.InforDocument;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.repositories.InforDocumentRepository;
import ch.cern.eam.wshub.core.repositories.InforDocEntityRepository;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentsServiceImpl implements DocumentsService {

    private Tools tools;

    private ApplicationData applicationData;

    private InforDocumentRepository inforDocumentRepository;

    private InforDocEntityRepository inforDocEntityRepository;

    public DocumentsServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public DocumentsServiceImpl(ApplicationData applicationData, Tools tools, InforDocumentRepository inforDocumentRepository) {
    }

    public DocumentsServiceImpl(ApplicationData applicationData, Tools tools, InforDocumentRepository inforDocumentRepository, InforDocEntityRepository inforDocEntityRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforDocumentRepository = inforDocumentRepository;
        this.inforDocEntityRepository = inforDocEntityRepository;
    }

    @Override
    public List<InforDocument> readInforDocuments(InforContext context, String entity, String objectCode) throws InforException {
        if ((entity == null || entity.isEmpty()) || (objectCode == null || objectCode.isEmpty())) {
            throw tools.generateFault("Parameters not supplied correctly.");
        }
        if (inforDocumentRepository != null) {
            List<InforDocument> docs = inforDocumentRepository.findByCodeAndEntity(objectCode, entity);
            if (docs != null) {
                return docs;
            }
        }
        EntityManager em = tools.getEntityManager();
        try {
            return em.createNamedQuery(InforDocument.GET_DOCUMENTS, InforDocument.class).setParameter("code", objectCode).setParameter("entity", entity).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public String createInforDocumentAndAssociation(InforContext context, InforDocument doc, String entity, String objectCode) throws InforException {
        createInforDocument(doc, context);
        String result = createInforDocumentAssociation(doc.getCode(), entity, objectCode, context);
        return result;
    }

    @Override
    public String createInforDocument(InforDocument doc, InforContext context) throws InforException {
        return null;
        // Set type
        // possible types: select *  from r5ucodes where uco_entity = 'DOTP';
        // D = Dynamic document
        // F = File system document
        // S = Static document
        // T = Template document
    }

    @Override
    public String createInforDocumentAssociation(String document, String entity, String objectCode, InforContext context) throws InforException {
        return null;
    }
}
