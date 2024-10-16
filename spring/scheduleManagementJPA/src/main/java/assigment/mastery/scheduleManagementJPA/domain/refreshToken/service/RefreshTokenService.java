package assigment.mastery.scheduleManagementJPA.domain.refreshToken.service;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.refreshToken.dto.ResponseReissueToken;
import assigment.mastery.scheduleManagementJPA.domain.refreshToken.repository.RefreshTokenRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public ResponseReissueToken reissue(String refreshToken, Member member) {
        refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_REFRESH_TOKEN));

        String accessToken = jwtUtil.createAccessToken(member.getId(), member.getRole());

        return ResponseReissueToken.builder()
                .accessToken(accessToken)
                .build();
    }
}
