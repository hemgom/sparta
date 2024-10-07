package assignment.introductory.scheduleManagementJDBC.domain.schedule;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Author {
    private int id;

    private String name;

    private String email;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
