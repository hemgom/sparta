package assigment.mastery.scheduleManagementJPA.domain.member.service;

import assigment.mastery.scheduleManagementJPA.config.PasswordEncoder;
import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.RefreshToken;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.*;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.RefreshTokenRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_MEMBER;
import static assigment.mastery.scheduleManagementJPA.security.enums.MemberRole.USER;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseMemberAndToken join(JoinMember request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member createdMember = Member.create(request, encodedPassword, USER);
        Member savedMember = memberRepository.save(createdMember);

        String accessToken = jwtUtil.createAccessToken(savedMember.getId(), USER);
        String refreshToken = jwtUtil.createRefreshToken(savedMember.getId(), USER);

        RefreshToken createdRefreshTokenEntity = RefreshToken.create(refreshToken);
        refreshTokenRepository.save(createdRefreshTokenEntity);

        return ResponseMemberAndToken.builder()
                .member(Member.makeResponse(savedMember))
                .token(ResponseToken.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build())
                .build();
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
