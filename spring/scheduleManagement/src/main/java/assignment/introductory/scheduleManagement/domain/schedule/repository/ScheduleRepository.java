package assignment.introductory.scheduleManagement.domain.schedule.repository;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestDeleteSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestFindAllSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestUpdateSchedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(RequestAddSchedule request);

    List<Schedule> findAll(RequestFindAllSchedule request);

    Optional<Schedule> findById(int scheduleId);

    void update(int id, RequestUpdateSchedule request);

    void delete(int id, RequestDeleteSchedule request);
}
