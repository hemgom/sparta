package assignment.introductory.scheduleManagementJDBC.domain.schedule.controller;

import assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.*;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.service.ScheduleService;
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
    public ScheduleDTO addSchedule(@RequestBody AddScheduleDTO request) {
        log.info("'일정 추가' 요청 확인");
        return scheduleService.save(request);
    }

    @GetMapping("/search-condition")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleListDTO findAllSchedule(@RequestParam(value = "authorName", defaultValue = "") String authorName,
                                           @RequestParam(value = "updateAt", defaultValue = "") String updateAt,
                                           @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "0") int pageSize) {
        log.info("'모든 일정 조회' 요청 확인");
        log.info("pageNum = {}, pageSize = {}", pageNum, pageSize);
        return scheduleService.findAll(authorName, updateAt, pageNum, pageSize);
    }

    @GetMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDTO findScheduleById(@PathVariable int scheduleId) {
        log.info("'특정 일정 조회' 요청 확인");
        return scheduleService.findById(scheduleId);
    }

    @PutMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ScheduleDTO updateSchedule(@PathVariable int scheduleId,
                                      @RequestBody UpdateScheduleDTO request) {
        return scheduleService.update(scheduleId, request);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSchedule(@PathVariable int scheduleId,
                                      @RequestBody DeleteScheduleDTO request) {
        scheduleService.delete(scheduleId, request);
    }
}
