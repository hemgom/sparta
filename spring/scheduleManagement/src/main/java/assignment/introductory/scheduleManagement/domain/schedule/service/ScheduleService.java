package assignment.introductory.scheduleManagement.domain.schedule.service;

import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestFindAllSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.ResponseSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.ResponseScheduleList;

public interface ScheduleService {
    ResponseSchedule save(RequestAddSchedule request);

    ResponseScheduleList findAll(RequestFindAllSchedule request);

    ResponseSchedule findById(int id);
}
