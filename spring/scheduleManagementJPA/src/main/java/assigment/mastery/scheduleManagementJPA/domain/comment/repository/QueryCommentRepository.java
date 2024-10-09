package assigment.mastery.scheduleManagementJPA.domain.comment.repository;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.UpdateComment;

import java.util.List;

public interface QueryCommentRepository {
    List<Comment> findAll(String author);

    void update(Comment foundComment, UpdateComment request);
}
