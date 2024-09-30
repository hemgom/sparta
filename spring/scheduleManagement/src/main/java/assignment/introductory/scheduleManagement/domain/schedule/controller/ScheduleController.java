package assignment.introductory.scheduleManagement.domain.schedule.controller;

import assignment.introductory.scheduleManagement.domain.schedule.dto.*;
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
    public ResponseSchedule addSchedule(@RequestBody RequestAddSchedule request) {
        log.info("'일정 추가' 요청 확인");
        return scheduleService.save(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseScheduleList findAllSchedule(@RequestBody RequestFindAllSchedule request) {
        log.info("'모든 일정 조회' 요청 확인");
        return scheduleService.findAll(request);
    }

    @GetMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSchedule findScheduleById(@PathVariable int scheduleId) {
        log.info("'특정 일정 조회' 요청 확인");
        return scheduleService.findById(scheduleId);
    }

    @PutMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseSchedule updateSchedule(@PathVariable int scheduleId,
                                           @RequestBody RequestUpdateSchedule request) {
        return scheduleService.update(scheduleId, request);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseSchedule deleteSchedule(@PathVariable int scheduleId,
                                           @RequestBody RequestDeleteSchedule request) {
        return scheduleService.delete(scheduleId, request);
    }
}
