package assigment.mastery.scheduleManagementJPA.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseSchedule {
    private long id;

    private String authorName;

    private String title;

    private String body;

    private String weather;

    private String createAt;

    private String updateAt;

    private int commentCount;
}
