package assigment.mastery.scheduleManagementJPA.domain.schedule.repository;

import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, QueryScheduleRepository {
}
