package assigment.mastery.scheduleManagementJPA.domain.schedule.repository;

import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;

import java.util.List;

public interface QueryScheduleRepository {
    List<Schedule> findAll(String author, String title);
}
