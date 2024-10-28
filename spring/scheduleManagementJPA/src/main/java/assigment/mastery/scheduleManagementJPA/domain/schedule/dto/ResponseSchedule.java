package assigment.mastery.scheduleManagementJPA.domain.schedule.dto;

import assigment.mastery.scheduleManagementJPA.converter.DateTimeFormatConverter;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
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

    public static ResponseSchedule makeResponse(Schedule schedule) {
        return ResponseSchedule.builder()
                .id(schedule.getId())
                .authorName(schedule.getMember().getName())
                .title(schedule.getTitle())
                .body(schedule.getBody())
                .weather(schedule.getWeather())
                .createAt(DateTimeFormatConverter.convertDateTimeFormat(schedule.getCreateAt()))
                .updateAt(DateTimeFormatConverter.convertDateTimeFormat(schedule.getUpdateAt()))
                .commentCount(schedule.getComments().size())
                .build();
    }
}
