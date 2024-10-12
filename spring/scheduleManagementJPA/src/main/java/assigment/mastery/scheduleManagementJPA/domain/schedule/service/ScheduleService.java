package assigment.mastery.scheduleManagementJPA.domain.schedule.service;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.ScheduleManager;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.AddSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseScheduleList;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.UpdateSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleManagerRepository;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.HasNotPermissionException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.*;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final ScheduleManagerRepository scheduleManagerRepository;

    public ResponseSchedule save(Long memberId, AddSchedule request) {
        Member writeMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new HasNotPermissionException(HAS_NOT_PERMISSION));

        Schedule created = Schedule.create(writeMember, request);
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

    public ResponseSchedule findById(Long scheduleId) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        return Schedule.makeResponse(found);
    }

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
    public void update(Long scheduleId, Long memberId, UpdateSchedule request) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        Schedule foundSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        checkMember(foundMember.getId(), foundSchedule.getMember().getId());

        scheduleRepository.update(foundSchedule, request);
    }

    public void delete(Long scheduleId, Long memberId) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        Schedule foundSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        checkMember(foundMember.getId(), foundSchedule.getMember().getId());

        scheduleRepository.deleteById(foundSchedule.getId());
    }

    private void checkMember(Long memberId, Long scheduleAuthorId) {
        if (!memberId.equals(scheduleAuthorId)) {
            throw new HasNotPermissionException(HAS_NOT_PERMISSION);
        }
    }
}
