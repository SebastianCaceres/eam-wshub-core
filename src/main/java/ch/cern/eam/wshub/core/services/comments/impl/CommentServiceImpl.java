package ch.cern.eam.wshub.core.services.comments.impl;

import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.comments.CommentService;
import ch.cern.eam.wshub.core.services.comments.entities.Comment;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.util.LinkedList;
import java.util.List;
import ch.cern.eam.wshub.core.repositories.CommentRepository;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.encodeBoolean;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;
import static ch.cern.eam.wshub.core.tools.Tools.extractEntityCode;
import static ch.cern.eam.wshub.core.tools.Tools.extractOrganizationCode;

public class CommentServiceImpl implements CommentService {

    private Tools tools;

    private ApplicationData applicationData;

    private CommentRepository commentRepository;

    public CommentServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public CommentServiceImpl(ApplicationData applicationData, Tools tools, CommentRepository commentRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.commentRepository = commentRepository;
    }

    public String createComment(InforContext context, Comment comment) throws InforException {
        return null;
        //
        // VALIDATION
    }

    public Comment[] readComments(InforContext context, String entityCode, String entityKeyCode, String typeCode) throws InforException {
        List<Comment> comments = commentRepository.findByEntityCodeAndEntityKeyCode(entityCode, extractEntityCode(entityKeyCode));
        return comments != null ? comments.toArray(new Comment[0]) : new Comment[0];
    }

    public String updateComment(InforContext context, Comment comment) throws InforException {
        return null;
    }

    public String deleteComment(InforContext context, Comment comment) throws InforException {
        commentRepository.deleteById(comment.getPk());
        return comment.getPk();
    }

    private static String complementEntityKeyCode(String entityCode, String entityKeyCode, String organization) {
        if ("OBJ".equals(entityCode) || "PART".equals(entityCode) || "TASK".equals(entityCode)) {
            return entityKeyCode + "#" + organization;
        }
        return entityKeyCode;
    }
}
