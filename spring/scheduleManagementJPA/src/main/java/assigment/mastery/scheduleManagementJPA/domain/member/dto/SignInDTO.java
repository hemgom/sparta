package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignInDTO {
    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 20)
    private String password;
}
