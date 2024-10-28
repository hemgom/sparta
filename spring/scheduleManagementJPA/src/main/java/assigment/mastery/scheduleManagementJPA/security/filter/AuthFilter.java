package assigment.mastery.scheduleManagementJPA.security.filter;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.HasNotPermissionException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.security.dto.TokenInfo;
import assigment.mastery.scheduleManagementJPA.security.jwt.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.HAS_NOT_PERMISSION;
import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_MEMBER;
import static assigment.mastery.scheduleManagementJPA.security.enums.MemberRole.ADMIN;

@Slf4j
@Component
@Order(3)
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String httpMethod = httpServletRequest.getMethod();
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url)) {
            if (url.matches("/member/join") || url.matches("/member/logIn")) {
                chain.doFilter(request, response);

            } else if (url.startsWith("/schedule") &&
                    (httpMethod.matches("PUT") || httpMethod.matches("DELETE"))) {
                TokenInfo tokenInfo = getTokenInfoFromRequest(httpServletRequest);

                if (!tokenInfo.getAuth().matches(ADMIN.getRole())) {
                    throw new HasNotPermissionException(HAS_NOT_PERMISSION);
                }

                Member member = memberRepository.findById(tokenInfo.getMemberId())
                        .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

                request.setAttribute("member", member);
                chain.doFilter(request, response);

            } else {
                TokenInfo tokenInfo = getTokenInfoFromRequest(httpServletRequest);

                Member member = memberRepository.findById(tokenInfo.getMemberId())
                        .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

                request.setAttribute("member", member);
                chain.doFilter(request, response);
            }
        }
    }

    private TokenInfo getTokenInfoFromRequest(HttpServletRequest httpServletRequest) {
        String token = jwtUtil.getTokenFromRequest(httpServletRequest);

        jwtUtil.checkTokenValidity(token);

        return jwtUtil.getPayload(token);
    }
}
