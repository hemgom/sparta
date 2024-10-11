package assigment.mastery.scheduleManagementJPA.domain.schedule.repository;

import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, QueryScheduleRepository {
    @Query(value =
            "SELECT s " +
            "FROM Schedule AS s " +
            "WHERE s.author LIKE CONCAT('%', :author, '%') AND s.title LIKE CONCAT('%', :title, '%')"
    )
    Slice<Schedule> findAllByAuthorAndTitle(@Param("author") String author, @Param("title") String title, Pageable pageable);

    @Query(value = "SELECT s " +
            "FROM Schedule AS s LEFT JOIN FETCH s.comments " +
            "WHERE s.id IN :ids ORDER BY s.updateAt DESC"
    )
    List<Schedule> findAllByScheduleIdIn(@Param("ids") List<Long> ids);
}
