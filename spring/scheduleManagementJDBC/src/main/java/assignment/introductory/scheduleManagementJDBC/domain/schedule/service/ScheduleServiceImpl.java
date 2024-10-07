package assignment.introductory.scheduleManagementJDBC.domain.schedule.service;

import assignment.introductory.scheduleManagementJDBC.domain.schedule.Author;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.Schedule;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.*;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.repository.AuthorRepository;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.repository.ScheduleRepository;
import assignment.introductory.scheduleManagementJDBC.domain.schedule.validator.ScheduleValidator;
import assignment.introductory.scheduleManagementJDBC.exception.customException.NotMatchedPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.AddScheduleDTO.AuthorInfo;
import static assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.AddScheduleDTO.ScheduleInfo;
import static assignment.introductory.scheduleManagementJDBC.exception.exceptionCode.ExceptionCode.NOT_MATCH_PASSWORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleValidator scheduleValidator;
    private final AuthorRepository authorRepository;

    @Override
    public ScheduleDTO save(AddScheduleDTO request) {
        ScheduleInfo scheduleInfo = request.getScheduleInfo();
        AuthorInfo authorInfo = request.getAuthorInfo();

        scheduleValidator.checkRequestAddSchedule(scheduleInfo.getBody(), authorInfo.getName(), scheduleInfo.getPassword());

        LocalDateTime currentDateTime = LocalDateTime.now();

        Optional<Author> foundAuthor = authorRepository.findByEmail(authorInfo.getEmail());
        Author author = foundAuthor.orElseGet(() -> authorRepository.save(authorInfo, currentDateTime));

        String requestAuthorName = request.getAuthorInfo().getName();
        String foundAuthorName = author.getName();
        if (!requestAuthorName.equals(foundAuthorName))
            authorRepository.update(author.getId(), requestAuthorName, currentDateTime);

        Schedule createSchedule = scheduleRepository.save(scheduleInfo, author, currentDateTime);

        return ScheduleDTO.builder()
                .id(createSchedule.getId())
                .body(createSchedule.getBody())
                .authorName(authorInfo.getName())
                .createAt(parseStringFormat(createSchedule.getCreateAt()))
                .updateAt(parseStringFormat(createSchedule.getUpdateAt()))
                .build();
    }

    @Override
    public ScheduleListDTO findAll(String authorName, String updateAt, int pageNum, int pageSize) {
        scheduleValidator.checkRequestFindAllSchedule(updateAt, authorName);

        List<Schedule> allSchedule = scheduleRepository.findAll(authorName, updateAt, pageNum, pageSize);

        ScheduleListDTO scheduleListDTO = new ScheduleListDTO();

        scheduleListDTO.setPageNum(pageNum);

        for (Schedule schedule : allSchedule) {
            scheduleListDTO.getScheduleList().add(
                    ScheduleDTO.builder()
                            .id(schedule.getId())
                            .body(schedule.getBody())
                            .authorName(schedule.getAuthor().getName())
                            .createAt(parseStringFormat(schedule.getCreateAt()))
                            .updateAt(parseStringFormat(schedule.getUpdateAt()))
                            .build());
        }

        return scheduleListDTO;
    }

    @Override
    public ScheduleDTO findById(int id) {
        Schedule schedule = scheduleRepository.findById(id);

        Author author = authorRepository.findById(schedule.getAuthor().getId());

        return ScheduleDTO.builder()
                .id(schedule.getId())
                .body(schedule.getBody())
                .authorName(author.getName())
                .createAt(parseStringFormat(schedule.getCreateAt()))
                .updateAt(parseStringFormat(schedule.getUpdateAt()))
                .build();
    }

    @Override
    public ScheduleDTO update(int id, UpdateScheduleDTO request) {
        scheduleValidator.checkRequestUpdateSchedule(request.getBody(), request.getAuthorName());

        Schedule foundSchedule = scheduleRepository.findById(id);

        if (!foundSchedule.getPassword().equals(request.getPassword()))
            throw new NotMatchedPasswordException(NOT_MATCH_PASSWORD);

        LocalDateTime currentDateTime = LocalDateTime.now();

        scheduleRepository.update(id, request, currentDateTime);
        authorRepository.update(foundSchedule.getAuthor().getId(), request.getAuthorName(), currentDateTime);

        return ScheduleDTO.builder()
                .id(foundSchedule.getId())
                .body(request.getBody())
                .authorName(request.getAuthorName())
                .createAt(parseStringFormat(foundSchedule.getCreateAt()))
                .updateAt(parseStringFormat(currentDateTime))
                .build();
    }

    @Override
    public void delete(int id, DeleteScheduleDTO request) {
        Schedule foundSchedule = scheduleRepository.findById(id);

        if (!foundSchedule.getPassword().equals(request.getPassword()))
            throw new NotMatchedPasswordException(NOT_MATCH_PASSWORD);

        scheduleRepository.delete(id, request);
    }

    private String parseStringFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
