package assignment.introductory.scheduleManagement.domain.schedule.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleListDTO {
    @Setter
    private int pageNum;

    private final List<ScheduleDTO> scheduleList = new ArrayList<>();
}
