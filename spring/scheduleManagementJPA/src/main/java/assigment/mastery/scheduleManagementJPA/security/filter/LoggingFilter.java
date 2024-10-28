package assigment.mastery.scheduleManagementJPA.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j(topic = "Logging")
@Component
@Order(1)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

        chain.doFilter(requestWrapper, responseWrapper);

        log.info("request URL: {}", httpRequest.getRequestURI());
        log.info("authorization: {}", httpRequest.getHeader("Authorization"));
        if (StringUtils.hasText(requestWrapper.getQueryString())) {
            log.info("queryParameter: {}", URLDecoder.decode(requestWrapper.getQueryString(), StandardCharsets.UTF_8));
        }
        log.info("requestBody: \n{}", new String(requestWrapper.getContentAsByteArray()));

        log.info("response status code: {}", httpResponse.getStatus());
        log.info("responseBody: {}", new String(responseWrapper.getContentAsByteArray()));
        responseWrapper.copyBodyToResponse();

        log.info("request response completed");
    }
}
