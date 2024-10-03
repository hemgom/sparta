package assignment.introductory.scheduleManagement.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
