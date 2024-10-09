package assigment.mastery.scheduleManagementJPA.domain.schedule.service;

import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.AddSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseScheduleList;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.UpdateSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return Schedule.makeResponse(saved);
    }

    public ResponseSchedule findById(long scheduleId) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        return Schedule.makeResponse(found);
    }

    public ResponseScheduleList findAll(String author, String title) {
        List<Schedule> foundList = scheduleRepository.findAll(author, title);

        List<ResponseSchedule> responseScheduleList = new ArrayList<>();
        foundList.stream().map(Schedule::makeResponse).forEach(responseScheduleList::add);

        return ResponseScheduleList.builder()
                .schedules(responseScheduleList)
                .build();
    }

    @Transactional
    public void update(long scheduleId, UpdateSchedule request) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        scheduleRepository.update(found, request);
    }

    public void delete(long scheduleId) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        scheduleRepository.deleteById(found.getId());
    }
}
