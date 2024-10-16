package assigment.mastery.scheduleManagementJPA.domain.schedule.service;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.ScheduleManager;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.*;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleManagerRepository;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.exception.customException.OpenApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final ScheduleManagerRepository scheduleManagerRepository;
    private final RestTemplate restTemplate;

    @Transactional
    public ResponseSchedule save(Member member, AddSchedule request) {
        String todayWeather = requestTodayWeather();

        Schedule created = Schedule.create(member, request, todayWeather);
        Schedule saved = scheduleRepository.save(created);

        if (!request.getScheduleManagers().isEmpty()) {
            List<Member> managers = memberRepository.findAllByNameIn(request.getScheduleManagers());

            if (managers.isEmpty()) throw new NotFoundEntityException(NOT_FOUND_MEMBER);

            List<ScheduleManager> scheduleManagers = new ArrayList<>();
            managers.forEach(m -> scheduleManagers.add(ScheduleManager.create(saved, m)));

            scheduleManagerRepository.saveAll(scheduleManagers);
        }

        return Schedule.makeResponse(saved);
    }

    @Transactional(readOnly = true)
    public ResponseSchedule findById(Long scheduleId) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        return Schedule.makeResponse(found);
    }

    @Transactional(readOnly = true)
    public ResponseScheduleList findAll(String author, String title, PageRequest pageRequest) {
        Slice<Schedule> foundSchedules = scheduleRepository.findAllByAuthorAndTitle(author, title, pageRequest);

        List<Long> scheduleIds = new ArrayList<>();
        foundSchedules.getContent().forEach(s -> scheduleIds.add(s.getId()));

        List<Schedule> finish = scheduleRepository.findAllByScheduleIdIn(scheduleIds);

        List<ResponseSchedule> responseScheduleList = new ArrayList<>();
        finish.stream().map(Schedule::makeResponse).forEach(responseScheduleList::add);

        return ResponseScheduleList.builder()
                .schedules(responseScheduleList)
                .pageable(foundSchedules.getPageable())
                .build();
    }

    @Transactional
    public void update(Long scheduleId, UpdateSchedule request) {
        Schedule foundSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        foundSchedule.update(request);

        scheduleRepository.save(foundSchedule);
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule foundSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        scheduleRepository.delete(foundSchedule);
    }

    private String requestTodayWeather() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://f-api.github.io/")
                .path("/f-api/weather.json")
                .encode().build().toUri();
        log.info("uri: {}", uri);

        WeatherDTO[] responseWeather = restTemplate.getForObject(uri, WeatherDTO[].class);

        if (responseWeather == null || responseWeather.length == 0)
            throw new OpenApiException(NOT_FOUND_OPEN_API_DATA);

        String today = LocalDateTime.now().toString().substring(5,10);

        String todayWeather = "";
        for (WeatherDTO w : responseWeather) {
            if (w.getDate().equals(today)) {
                todayWeather = w.getWeather();
                break;
            }
        }

        if (!StringUtils.hasText(todayWeather))
            throw new OpenApiException(NOT_FOUND_WEATHER);

        return todayWeather;
    }
}
