package assignment.introductory.scheduleManagement.domain.schedule.service;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.ResponseAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ResponseAddSchedule save(RequestAddSchedule request) {
        Schedule createSchedule = scheduleRepository.save(request);
        return ResponseAddSchedule.builder()
                .id(createSchedule.getId())
                .body(createSchedule.getBody())
                .author(createSchedule.getAuthor())
                .createAt(createSchedule.getCreateAt())
                .build();
    }
}
