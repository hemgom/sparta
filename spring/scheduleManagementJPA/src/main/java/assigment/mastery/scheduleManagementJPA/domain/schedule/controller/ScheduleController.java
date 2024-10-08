package assigment.mastery.scheduleManagementJPA.domain.schedule.controller;

import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.AddSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseScheduleList;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.UpdateSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSchedule addSchedule(@RequestBody @Valid AddSchedule request) {
        return scheduleService.save(request);
    }

    @GetMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSchedule findScheduleById(@PathVariable(name = "scheduleId") long scheduleId) {
        return scheduleService.findById(scheduleId);
    }

    @GetMapping("/search-condition")
    @ResponseStatus(HttpStatus.OK)
    public ResponseScheduleList findAllSchedules(@RequestParam(name = "author", defaultValue = "") String author,
                                                 @RequestParam(name = "title", defaultValue = "") String title) {
        return scheduleService.findAll(author, title);
    }

    @PutMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSchedule(@PathVariable(name = "scheduleId") long scheduleId,
                               @RequestBody @Valid UpdateSchedule request) {
        scheduleService.update(scheduleId, request);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable(name = "scheduleId") long scheduleId) {
        scheduleService.delete(scheduleId);
    }
}
