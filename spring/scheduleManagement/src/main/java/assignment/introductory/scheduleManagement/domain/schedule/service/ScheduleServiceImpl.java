package assignment.introductory.scheduleManagement.domain.schedule.service;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.*;
import assignment.introductory.scheduleManagement.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ResponseSchedule save(RequestAddSchedule request) {
        Schedule createSchedule = scheduleRepository.save(request);
        return ResponseSchedule.builder()
                .id(createSchedule.getId())
                .body(createSchedule.getBody())
                .author(createSchedule.getAuthor())
                .createAt(createSchedule.getCreateAt())
                .updateAt(createSchedule.getUpdateAt())
                .build();
    }

    @Override
    public ResponseScheduleList findAll(RequestFindAllSchedule request) {
        List<Schedule> allSchedule = scheduleRepository.findAll(request);

        ResponseScheduleList responseScheduleList = new ResponseScheduleList();
        for (Schedule schedule : allSchedule) {
            responseScheduleList.getScheduleList().add(
                    ResponseSchedule.builder()
                            .id(schedule.getId())
                            .body(schedule.getBody())
                            .author(schedule.getAuthor())
                            .createAt(schedule.getCreateAt())
                            .updateAt(schedule.getUpdateAt())
                            .build());
        }

        return responseScheduleList;
    }

    @Override
    public ResponseSchedule findById(int id) {
        Optional<Schedule> foundSchedule = scheduleRepository.findById(id);

        if (foundSchedule.isEmpty()) return new ResponseSchedule();

        return ResponseSchedule.builder()
                .id(foundSchedule.get().getId())
                .body(foundSchedule.get().getBody())
                .author(foundSchedule.get().getAuthor())
                .createAt(foundSchedule.get().getCreateAt())
                .updateAt(foundSchedule.get().getUpdateAt())
                .build();
    }

    @Override
    public ResponseSchedule update(int id, RequestUpdateSchedule request) {
        scheduleRepository.update(id, request);

        return findById(id);
    }

    @Override
    public ResponseSchedule delete(int id, RequestDeleteSchedule request) {
        scheduleRepository.delete(id, request);

        return findById(id);
    }
}
