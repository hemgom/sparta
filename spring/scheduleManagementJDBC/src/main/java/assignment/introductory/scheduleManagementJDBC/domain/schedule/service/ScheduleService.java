package assignment.introductory.scheduleManagementJDBC.domain.schedule.service;

import assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.*;

public interface ScheduleService {
    ScheduleDTO save(AddScheduleDTO request);

    ScheduleListDTO findAll(String authorName, String updateAt, int pageNum, int pageSize);

    ScheduleDTO findById(int id);

    ScheduleDTO update(int id, UpdateScheduleDTO request);

    void delete(int id, DeleteScheduleDTO request);
}
