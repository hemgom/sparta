package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddMember {
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Email
    private String email;
}
