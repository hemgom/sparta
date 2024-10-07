package assignment.introductory.scheduleManagementJDBC.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private int id;

    private String body;

    private String authorName;

    private String createAt;

    private String updateAt;
}
