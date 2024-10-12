package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMember {
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
}
