package assigment.mastery.scheduleManagementJPA.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddComment {
    @NotBlank
    @Size(min = 5, max = 150)
    private String body;
}
