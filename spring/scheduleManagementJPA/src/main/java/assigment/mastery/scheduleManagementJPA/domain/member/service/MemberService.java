package assigment.mastery.scheduleManagementJPA.domain.member.service;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.AddMember;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.ResponseMember;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.ResponseMemberList;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.UpdateMember;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_MEMBER;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public ResponseMember save(AddMember request) {
        Member created = Member.create(request);

        Member saved = memberRepository.save(created);

        return Member.makeResponse(saved);
    }

    public ResponseMember findById(Long memberId) {
        Member found = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        return Member.makeResponse(found);
    }

    public ResponseMemberList findAll(String name, PageRequest pageRequest) {
        Slice<Member> foundMembers = memberRepository.findAllByName(name, pageRequest);

        List<ResponseMember> responseMembers = new ArrayList<>();
        foundMembers.getContent().stream().map(Member::makeResponse).forEach(responseMembers::add);

        return ResponseMemberList.builder()
                .members(responseMembers)
                .pageable(foundMembers.getPageable())
                .build();
    }

    @Transactional
    public void update(Long memberId, UpdateMember request) {
        Member found = memberRepository.findById(memberId)
                        .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        memberRepository.update(found, request);
    }

    public void delete(Long memberId) {
        Member found = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        memberRepository.delete(found);
    }
}
