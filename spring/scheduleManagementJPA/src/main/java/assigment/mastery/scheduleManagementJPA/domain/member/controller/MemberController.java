package assigment.mastery.scheduleManagementJPA.domain.member.controller;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.*;
import assigment.mastery.scheduleManagementJPA.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMemberAndToken joinMember(@RequestBody @Valid RequestJoin request) {
        return memberService.join(request);
    }

    @PostMapping("/logIn")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMemberAndToken logIn(@RequestBody @Valid RequestLogIn request) {
        return memberService.logIn(request);
    }

    @PostMapping("/logOut")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logOut(@RequestHeader(name = "Authorization") String refreshToken) {
        memberService.logOut(refreshToken);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMember findMemberById(@PathVariable(name = "memberId") Long memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("/search-condition")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMemberList findAllMembers(@RequestParam(name = "name", defaultValue = "") String name,
                                             @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(ASC, "id"));
        return memberService.findAll(name, pageRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@RequestAttribute(name = "member") Member member,
                             @RequestBody @Valid RequestUpdate request) {
        memberService.update(member, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@RequestAttribute(name = "member") Member member) {
        memberService.delete(member);
    }
}
