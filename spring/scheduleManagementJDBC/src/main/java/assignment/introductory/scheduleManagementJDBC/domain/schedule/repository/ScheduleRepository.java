package assignment.introductory.scheduleManagementJDBC.domain.schedule.repository;

import assignment.introductory.scheduleManagementJDBC.domain.schedule.Author;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.Schedule;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.DeleteScheduleDTO;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.UpdateScheduleDTO;

import java.time.LocalDateTime;
import java.util.List;

import static assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.AddScheduleDTO.ScheduleInfo;

public interface ScheduleRepository {
    Schedule save(ScheduleInfo scheduleInfo, Author author, LocalDateTime createAt);

    List<Schedule> findAll(String authorName, String updateAt, int pageNum, int pageSize);

    Schedule findById(int scheduleId);

    void update(int id, UpdateScheduleDTO request, LocalDateTime updateAt);

    void delete(int id, DeleteScheduleDTO request);
}
