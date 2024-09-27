package assignment.introductory.scheduleManagement.domain.schedule.repository;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Schedule save(RequestAddSchedule request) {
        String sql = "insert into schedule(body, author, password, create_at, update_at) values (?,?,?,?,?)";

        LocalDateTime now = LocalDateTime.now();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, request.getBody());
            ps.setString(2, request.getAuthor());
            ps.setString(3,request.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(now));
            ps.setTimestamp(5, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        int key = keyHolder.getKey().intValue();
        return Schedule.builder()
                .id(key)
                .body(request.getBody())
                .author(request.getAuthor())
                .password(request.getPassword())
                .createAt(now)
                .updateAt(now)
                .build();
    }
}
