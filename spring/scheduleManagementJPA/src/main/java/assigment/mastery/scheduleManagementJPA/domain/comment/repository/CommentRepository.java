package assigment.mastery.scheduleManagementJPA.domain.comment.repository;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, QueryCommentRepository {
    @Query( value = "SELECT c FROM Comment AS c WHERE c.author LIKE CONCAT('%', :author, '%')")
    public List<Comment> findAllByAuthor(@Param("author") String author);
}
