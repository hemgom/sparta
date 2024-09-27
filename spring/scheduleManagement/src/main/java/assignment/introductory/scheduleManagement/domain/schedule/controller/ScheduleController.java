package assignment.introductory.scheduleManagement.domain.schedule.controller;

import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.ResponseAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAddSchedule addSchedule(@RequestBody RequestAddSchedule request) {
        log.info("일정 추가 요청 확인");
        return scheduleService.save(request);
    }
}
