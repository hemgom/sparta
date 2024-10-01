package assignment.introductory.scheduleManagement.domain.schedule.service;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.*;
import assignment.introductory.scheduleManagement.domain.schedule.repository.ScheduleRepository;
import assignment.introductory.scheduleManagement.domain.schedule.validator.ScheduleValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleValidator scheduleValidator;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               ScheduleValidator scheduleValidator) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleValidator = scheduleValidator;
    }

    @Override
    public ResponseSchedule save(RequestAddSchedule request) {
        scheduleValidator.checkRequestAddSchedule(request.getBody(), request.getAuthor(), request.getPassword());

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
        scheduleValidator.checkRequestFindAllSchedule(request.getUpdateAt(), request.getAuthor());

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
        scheduleValidator.checkRequestUpdateSchedule(request.getBody(), request.getAuthor());

        scheduleRepository.update(id, request);

        return findById(id);
    }

    @Override
    public ResponseSchedule delete(int id, RequestDeleteSchedule request) {
        scheduleRepository.delete(id, request);

        return findById(id);
    }
}
