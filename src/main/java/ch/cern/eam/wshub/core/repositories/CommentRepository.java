package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.comments.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByEntityCodeAndEntityKeyCode(String entityCode, String entityKeyCode);
}
