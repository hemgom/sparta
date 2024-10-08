package assigment.mastery.scheduleManagementJPA.domain.schedule.service;

import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.AddSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseScheduleList;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_SCHEDULE;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ResponseSchedule save(AddSchedule request) {
        Schedule created = Schedule.create(request);

        Schedule saved = scheduleRepository.save(created);

        return ResponseSchedule.builder()
                .id(saved.getId())
                .author(saved.getAuthor())
                .title(saved.getTitle())
                .body(saved.getBody())
                .createAt(convertToFormat(saved.getCreateAt()))
                .updateAt(convertToFormat(saved.getCreateAt()))
                .build();
    }

    public ResponseSchedule findById(Long id) {
        Schedule found = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        return ResponseSchedule.builder()
                .id(found.getId())
                .author(found.getAuthor())
                .title(found.getTitle())
                .body(found.getBody())
                .createAt(convertToFormat(found.getCreateAt()))
                .updateAt(convertToFormat(found.getCreateAt()))
                .build();
    }

    public ResponseScheduleList findAll(String author, String title) {
        List<Schedule> foundList = scheduleRepository.findAll(author, title);

        List<ResponseSchedule> responseScheduleList = new ArrayList<>();
        for (Schedule schedule : foundList) {
            responseScheduleList.add(ResponseSchedule.builder()
                    .id(schedule.getId())
                    .author(schedule.getAuthor())
                    .title(schedule.getTitle())
                    .body(schedule.getBody())
                    .createAt(convertToFormat(schedule.getCreateAt()))
                    .updateAt(convertToFormat(schedule.getCreateAt()))
                    .build());
        }

        return ResponseScheduleList.builder()
                .schedules(responseScheduleList)
                .build();
    }

    public void delete(Long id) {
        try {
            scheduleRepository.deleteById(id);

        } catch (NotFoundEntityException e) {
            throw new NotFoundEntityException(NOT_FOUND_SCHEDULE);
        }
    }

    private String convertToFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
