package assignment.introductory.scheduleManagement.domain.schedule;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Setter
    private int id;

    private String body;
    private String author;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
