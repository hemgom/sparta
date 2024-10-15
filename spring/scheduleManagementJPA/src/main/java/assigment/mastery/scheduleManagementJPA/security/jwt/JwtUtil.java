package assigment.mastery.scheduleManagementJPA.security.jwt;

import assigment.mastery.scheduleManagementJPA.exception.customException.NotValidTokenException;
import assigment.mastery.scheduleManagementJPA.security.enums.MemberRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.*;
import static assigment.mastery.scheduleManagementJPA.security.enums.AuthenticationConstant.*;

@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.accessToken.valid.period}")
    private long accessTokenValidPeriod;

    @Value("${jwt.refreshToken.valid.period}")
    private long refreshTokenValidPeriod;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createAccessToken(Long memberId, MemberRole role) {
        Date now = now();

        return JWT_TYPE.getKey() + " " + Jwts.builder()
                .setSubject(Long.toString(memberId))
                .claim(AUTH.getKey(), role)
                .setExpiration(new Date(now.getTime() + accessTokenValidPeriod))
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public String createRefreshToken(Long memberId, MemberRole role) {
        Date now = now();

        return JWT_TYPE.getKey() + " " + Jwts.builder()
                .setSubject(Long.toString(memberId))
                .claim(AUTH.getKey(), role)
                .setExpiration(new Date(now.getTime() + refreshTokenValidPeriod))
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public void checkTokenValidity(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature: 유효하지 않은 JWT 서명 입니다.");
            throw new NotValidTokenException(NOT_VALID_TOKEN);
        
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT: 만료된 JWT 입니다.");
            throw new NotValidTokenException(EXPIRED_TOKEN);

        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT: 지원되지 않는 JWT 입니다.");
            throw new NotValidTokenException(NOT_SUPPORT_TOKEN);

        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: 잘못된 JWT 입니다.");
            throw new NotValidTokenException(HAS_NOT_TOKEN);
        }
    }

    public Claims getPayload(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(HEADER.getKey());

        if (!StringUtils.hasText(token)) {
            throw new NotValidTokenException(HAS_NOT_TOKEN);
        }

        return removeTokenType(token);
    }

    private Date now() {
        return new Date();
    }

    private String removeTokenType(String token) {
        if (token.length() < 7) {
            throw new NotValidTokenException(NOT_VALID_TOKEN);
        }

        return token.substring(7);
    }
}
