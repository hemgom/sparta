package assigment.mastery.scheduleManagementJPA.domain.member.service;

import assigment.mastery.scheduleManagementJPA.config.PasswordEncoder;
import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.*;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.domain.refreshToken.RefreshToken;
import assigment.mastery.scheduleManagementJPA.domain.refreshToken.repository.RefreshTokenRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.DuplicateEmailException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotMatchPasswordException;
import assigment.mastery.scheduleManagementJPA.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.*;
import static assigment.mastery.scheduleManagementJPA.security.enums.MemberRole.USER;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository RefreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseMemberAndToken join(RequestJoin request) {
        checkDuplicateEmail(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member createdMember = Member.create(request, encodedPassword, USER);
        Member savedMember = memberRepository.save(createdMember);

        String accessToken = jwtUtil.createAccessToken(savedMember.getId(), USER);
        String refreshToken = jwtUtil.createRefreshToken(savedMember.getId(), USER);

        RefreshToken createdRefreshTokenEntity = RefreshToken.create(refreshToken);
        RefreshTokenRepository.save(createdRefreshTokenEntity);

        return ResponseMemberAndToken.builder()
                .member(Member.makeResponse(savedMember))
                .token(ResponseToken.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build())
                .build();
    }

    @Transactional
    public ResponseMemberAndToken logIn(RequestLogIn request) {
        Member foundMember = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        if (!passwordEncoder.matches(request.getPassword(), foundMember.getPassword())) {
            throw new NotMatchPasswordException(NOT_MATCH_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(foundMember.getId(), foundMember.getRole());
        String refreshToken = jwtUtil.createRefreshToken(foundMember.getId(), foundMember.getRole());

        RefreshToken createdRefreshTokenEntity = RefreshToken.create(refreshToken);
        RefreshTokenRepository.save(createdRefreshTokenEntity);

        return ResponseMemberAndToken.builder()
                .member(Member.makeResponse(foundMember))
                .token(ResponseToken.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build())
                .build();
    }

    @Transactional
    public void logOut(String refreshToken) {
        RefreshToken found = RefreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_REFRESH_TOKEN));

        RefreshTokenRepository.deleteByRefreshToken(found.getRefreshToken());
    }

    @Transactional(readOnly = true)
    public ResponseMember findById(Long memberId) {
        Member found = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        return Member.makeResponse(found);
    }

    @Transactional(readOnly = true)
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
    public void update(Member member, RequestUpdate request) {
        member.update(request);
        memberRepository.save(member);
    }

    @Transactional
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    private void checkDuplicateEmail(String email) {
        Optional<Member> found = memberRepository.findByEmail(email);

        if (found.isPresent()) throw new DuplicateEmailException(DUPLICATE_EMAIL);
    }
}
