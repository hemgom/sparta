package assignment.introductory.scheduleManagement.domain.schedule.repository;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;

public interface ScheduleRepository {
    Schedule save(RequestAddSchedule request);
}
