package assigment.mastery.scheduleManagementJPA.domain.refreshToken.controller;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.refreshToken.dto.ResponseReissueToken;
import assigment.mastery.scheduleManagementJPA.domain.refreshToken.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public ResponseReissueToken reissueAccessToken(@RequestHeader(name = "Authorization") String refreshToken,
                                                   @RequestAttribute(name = "member") Member member) {
        return refreshTokenService.reissue(refreshToken, member);
    }
}
