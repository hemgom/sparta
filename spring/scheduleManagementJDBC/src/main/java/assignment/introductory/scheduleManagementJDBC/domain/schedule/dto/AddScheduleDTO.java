package assignment.introductory.scheduleManagementJDBC.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddScheduleDTO {
    private ScheduleInfo scheduleInfo;
    private AuthorInfo authorInfo;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleInfo {
        private String body;
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorInfo {
        private String name;
        private String email;
    }
}
