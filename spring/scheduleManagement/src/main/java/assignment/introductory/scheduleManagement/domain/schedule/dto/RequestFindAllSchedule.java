package assignment.introductory.scheduleManagement.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestFindAllSchedule {
    private String author;
    private String updateAt;
}
