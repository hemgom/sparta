package assigment.mastery.scheduleManagementJPA.domain.comment.repository;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, QueryCommentRepository {
}
