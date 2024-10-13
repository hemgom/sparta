package assigment.mastery.scheduleManagementJPA.security.jwt;

import assigment.mastery.scheduleManagementJPA.security.enums.MemberRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static assigment.mastery.scheduleManagementJPA.security.enums.AuthorizationKey.*;
import static java.nio.charset.StandardCharsets.UTF_8;

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

        return BEARER.getKey() + Jwts.builder()
                .setSubject(Long.toString(memberId))
                .claim(AUTH.getKey(), role)
                .setExpiration(new Date(now.getTime() + accessTokenValidPeriod))
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public String createRefreshToken(Long memberId, MemberRole role) {
        Date now = now();

        return BEARER.getKey() + Jwts.builder()
                .setSubject(Long.toString(memberId))
                .claim(AUTH.getKey(), role)
                .setExpiration(new Date(now.getTime() + refreshTokenValidPeriod))
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature: 유효하지 않은 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT: 만료된 JWT 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT: 지원되지 않는 JWT 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: 잘못된 JWT 입니다.");
        }

        return false;
    }

    public Claims getMemberInfo(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Date now() {
        return new Date();
    }
}
