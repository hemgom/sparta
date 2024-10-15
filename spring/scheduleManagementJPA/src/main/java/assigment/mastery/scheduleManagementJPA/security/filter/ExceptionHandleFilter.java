package assigment.mastery.scheduleManagementJPA.security.filter;

import assigment.mastery.scheduleManagementJPA.exception.customException.HasNotPermissionException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotValidTokenException;
import assigment.mastery.scheduleManagementJPA.exception.dto.ResponseExceptionCode;
import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class ExceptionHandleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);

        } catch (NotValidTokenException e) {
            setExceptionToResponse(httpResponse, e.getExceptionCode());

        } catch (HasNotPermissionException e) {
            setExceptionToResponse(httpResponse, e.getExceptionCode());

        } catch (NotFoundEntityException e) {
            setExceptionToResponse(httpResponse, e.getExceptionCode());
        }
    }

    private void setExceptionToResponse(HttpServletResponse httpServletResponse, ExceptionCode exceptionCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setStatus(exceptionCode.getHttpStatus().value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseExceptionCode responseExceptionCode = ResponseExceptionCode.builder()
                .code(exceptionCode.name())
                .message(exceptionCode.getMessage())
                .build();

        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseExceptionCode));
    }
}
