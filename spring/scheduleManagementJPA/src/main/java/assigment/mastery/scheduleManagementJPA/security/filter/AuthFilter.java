package assigment.mastery.scheduleManagementJPA.security.filter;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_MEMBER;

@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url)) {
            if (url.matches("/member") || url.matches("/member/logIn")) {
                chain.doFilter(request, response);

            } else {
                String token = jwtUtil.getTokenFromRequest(httpServletRequest);

                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Invalid token");
                }

                Claims info = jwtUtil.getMemberInfo(token);
                Long memberId = Long.parseLong(info.getSubject());

                Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

                request.setAttribute("member", member);
                chain.doFilter(request, response);
            }
        }
    }
}
