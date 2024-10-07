package assignment.introductory.scheduleManagementJDBC.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateScheduleDTO {
    private String password;

    private String body;

    private String authorName;
}
