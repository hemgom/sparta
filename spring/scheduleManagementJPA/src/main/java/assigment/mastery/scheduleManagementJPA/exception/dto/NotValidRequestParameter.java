package assigment.mastery.scheduleManagementJPA.exception.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
public class NotValidRequestParameter {
    private String code;

    private String message;

    private List<NotValidParameter> notValidParameters;

    @Getter
    @Builder
    public static class NotValidParameter {
        private String field;
        private String message;

        public static NotValidParameter of(FieldError fieldError) {
            return NotValidParameter.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
