package assigment.mastery.scheduleManagementJPA.domain.schedule.repository;

import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.UpdateSchedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QueryScheduleRepositoryImpl implements QueryScheduleRepository {

    private final EntityManager entityManager;

    public QueryScheduleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Schedule> findAll(String author, String title) {
        String jpql = "SELECT s FROM Schedule AS s";

        List<String> searchCondition = new ArrayList<>();

        if (StringUtils.hasText(author)) {
            searchCondition.add("s.author LIKE CONCAT('%', :author, '%')");
        }

        if (StringUtils.hasText(title)) {
            searchCondition.add("s.title LIKE CONCAT('%', :title, '%')");
        }

        if (!searchCondition.isEmpty()) {
            jpql += " WHERE " + String.join(" AND ", searchCondition);
        }

        jpql += " ORDER BY s.updateAt DESC";

        TypedQuery<Schedule> query = entityManager.createQuery(jpql, Schedule.class);

        if (StringUtils.hasText(author)) {
            query.setParameter("author", author);
        }

        if (StringUtils.hasText(title)) {
            query.setParameter("title", title);
        }

        return query.getResultList();
    }

    public void update(Schedule foundSchedule, UpdateSchedule request) {
        foundSchedule.update(request);
    }
}
