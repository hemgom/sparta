package assigment.mastery.scheduleManagementJPA.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseScheduleList {
    private List<ResponseSchedule> schedules;
}
