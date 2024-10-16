package assigment.mastery.scheduleManagementJPA.domain.comment.repository;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c FROM Comment AS c WHERE c.authorId IN :authorIds")
    List<Comment> findAllByAuthor(@Param("authorIds")Set<Long> authorIds);
}
