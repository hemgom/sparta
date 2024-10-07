package assignment.introductory.scheduleManagementJDBC.domain.schedule.repository;

import assignment.introductory.scheduleManagementJDBC.domain.schedule.Author;

import java.time.LocalDateTime;
import java.util.Optional;

import static assignment.introductory.scheduleManagementJDBC.domain.schedule.dto.AddScheduleDTO.AuthorInfo;

public interface AuthorRepository {
    Author save(AuthorInfo requestInfo, LocalDateTime createAt);

    Optional<Author> findByEmail(String email);

    Author findById(int id);

    void update(int id, String name, LocalDateTime createAt);
}
