package assigment.mastery.scheduleManagementJPA.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
public class ResponseScheduleList {
    private List<ResponseSchedule> schedules;

    private Pageable pageable;
}
