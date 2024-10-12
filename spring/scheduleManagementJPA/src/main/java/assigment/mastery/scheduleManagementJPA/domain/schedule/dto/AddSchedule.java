package assigment.mastery.scheduleManagementJPA.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class AddSchedule {
    @NotBlank
    @Size(min = 5, max = 100)
    private String title;

    @NotBlank
    @Size(min = 10, max = 250)
    private String body;

    @NotNull
    private List<String> scheduleManagers;
}
