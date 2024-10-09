package assigment.mastery.scheduleManagementJPA.domain.comment.repository;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.UpdateComment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryCommentRepositoryImpl implements QueryCommentRepository {
    private final EntityManager entityManager;

    public QueryCommentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> findAll(String author) {
        String jpql = "SELECT c FROM Comment AS c";

        List<String> searchCondition = new ArrayList<>();

        if (StringUtils.hasText(author)) {
            searchCondition.add("c.author = :author");
        }

        if (!searchCondition.isEmpty()) {
            jpql += " WHERE " + String.join(" AND ", searchCondition);
        }

        TypedQuery<Comment> query = entityManager.createQuery(jpql, Comment.class);

        if (StringUtils.hasText(author)) {
            query.setParameter("author", author);
        }

        return query.getResultList();
    }

    @Override
    public void update(Comment foundComment, UpdateComment request) {
        foundComment.update(request);
    }
}
