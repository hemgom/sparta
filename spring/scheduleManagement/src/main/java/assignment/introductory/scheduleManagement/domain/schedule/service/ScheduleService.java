package assignment.introductory.scheduleManagement.domain.schedule.service;

import assignment.introductory.scheduleManagement.domain.schedule.dto.*;

public interface ScheduleService {
    ResponseSchedule save(RequestAddSchedule request);

    ResponseScheduleList findAll(RequestFindAllSchedule request);

    ResponseSchedule findById(int id);

    ResponseSchedule update(int id, RequestUpdateSchedule request);

    ResponseSchedule delete(int id, RequestDeleteSchedule request);
}
