package assignment.introductory.scheduleManagement.domain.schedule.repository;

import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestAddSchedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.RequestFindAllSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    @Override
    public List<Schedule> findAll(RequestFindAllSchedule request) {
        String author = request.getAuthor();
        String updateAt = request.getUpdateAt();

        String sql = "select id, body, author, create_at, update_at from schedule";

        if (StringUtils.hasText(author) || StringUtils.hasText(updateAt)) {
            sql += " where";
        }

        boolean andFlag = false;
        List<Object> param = new ArrayList<>();

        if (StringUtils.hasText(author)) {
            sql += " author = ?";
            param.add(author);
            andFlag = true;
        }

        if (StringUtils.hasText(updateAt)) {
            if (andFlag) sql += " and";

            sql += " update_at like concat(?, '%')";
            param.add(updateAt);
        }

        sql += " order by update_at desc";
        log.info("생성 쿼리={}", sql);

        return jdbcTemplate.query(sql, scheduleRowMapper(), param.toArray());
    }

    @Override
    public Optional<Schedule> findById(int id) {
        String sql = "select id, body, author, create_at, update_at from schedule where id = ?";

        try {
            Schedule foundSchedule = jdbcTemplate.query(sql, scheduleRowMapper(), id).get(0);
            return Optional.of(foundSchedule);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return ((rs, rowNum) -> {
            return Schedule.builder()
                    .id(rs.getInt("id"))
                    .body(rs.getString("body"))
                    .author(rs.getString("author"))
                    .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                    .updateAt(rs.getTimestamp("update_at").toLocalDateTime())
                    .build();
        });
    }
}
