package assigment.mastery.scheduleManagementJPA.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateSchedule {
    @NotBlank
    @Size(min = 5, max = 100)
    private String title;

    @NotBlank
    @Size(min = 10, max = 250)
    private String body;
}
