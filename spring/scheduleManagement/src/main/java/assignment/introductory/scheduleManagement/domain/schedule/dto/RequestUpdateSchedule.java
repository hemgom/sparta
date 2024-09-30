package assignment.introductory.scheduleManagement.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateSchedule {
    private String password;
    private String body;
    private String author;
}
