package assignment.introductory.scheduleManagement.domain.schedule.service;

import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.ResponseAddSchedule;

public interface ScheduleService {
    ResponseAddSchedule save(RequestAddSchedule request);
}
