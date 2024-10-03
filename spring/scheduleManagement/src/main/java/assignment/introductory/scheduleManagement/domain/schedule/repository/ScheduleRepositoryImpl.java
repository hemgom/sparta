package assignment.introductory.scheduleManagement.domain.schedule.repository;

import assignment.introductory.scheduleManagement.domain.schedule.Author;
import assignment.introductory.scheduleManagement.domain.schedule.Schedule;
import assignment.introductory.scheduleManagement.domain.schedule.dto.DeleteScheduleDTO;
import assignment.introductory.scheduleManagement.domain.schedule.dto.UpdateScheduleDTO;
import assignment.introductory.scheduleManagement.exception.customException.NotFoundSearchInfoException;
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
import java.util.Objects;

import static assignment.introductory.scheduleManagement.domain.schedule.dto.AddScheduleDTO.ScheduleInfo;
import static assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode.NOT_FOUND_SCHEDULE;

@Slf4j
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Schedule save(ScheduleInfo scheduleInfo, Author author, LocalDateTime createAt) {
        String sql = "insert into schedule(body, author_id, password, create_at, update_at) values (?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, scheduleInfo.getBody());
            ps.setInt(2, author.getId());
            ps.setString(3,scheduleInfo.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(createAt));
            ps.setTimestamp(5, Timestamp.valueOf(createAt));
            return ps;
        }, keyHolder);

        int key = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return Schedule.builder()
                .id(key)
                .body(scheduleInfo.getBody())
                .author(author)
                .password(scheduleInfo.getPassword())
                .createAt(createAt)
                .updateAt(createAt)
                .build();
    }

    @Override
    public List<Schedule> findAll(String authorName, String updateAt, int pageNum, int pageSize) {
        String sql =
                """
                select a.id as id, a.body as body, a.create_at as create_at, a.update_at as update_at,
                       b.id, b.name, b.e_mail, b.create_at as author_create_at, b.update_at as author_update_at
                from schedule a join author b on a.author_id = b.id
                """;

        if (StringUtils.hasText(authorName) || StringUtils.hasText(updateAt)) {
            sql += " where";
        }

        boolean andFlag = false;
        List<Object> param = new ArrayList<>();

        if (StringUtils.hasText(authorName)) {
            sql += " b.name = ?";
            param.add(authorName);
            andFlag = true;
        }

        if (StringUtils.hasText(updateAt)) {
            if (andFlag) sql += " and";

            sql += " a.update_at like concat(?, '%')";
            param.add(updateAt);
        }

        sql += " order by update_at desc";

        if (pageNum > 0 && pageSize > 0) {
            int startIndex = (pageNum - 1) * pageSize;
            sql += " limit ? offset ?";
            param.add(pageSize);
            param.add(startIndex);
        }

        return jdbcTemplate.query(sql, mapScheduleJoinAuthorRow(), param.toArray());
    }

    @Override
    public Schedule findById(int id) {
        String sql = "select * from schedule where id = ?";

        try{
            Schedule foundSchedule = jdbcTemplate.queryForObject(sql, mapScheduleRow(), id);
            if (foundSchedule == null) {
                throw new NullPointerException();
            }
            return foundSchedule;

        } catch (EmptyResultDataAccessException | NullPointerException e) {
            throw new NotFoundSearchInfoException(NOT_FOUND_SCHEDULE);
        }
    }

    @Override
    public void update(int id, UpdateScheduleDTO request, LocalDateTime updateAt) {
        String sql = "update schedule set body = ?, update_at = ? where id = ? and password = ?";

        jdbcTemplate.update(sql,
                request.getBody(),
                updateAt,
                id,
                request.getPassword());
    }

    @Override
    public void delete(int id, DeleteScheduleDTO request) {
        String sql = "delete from schedule where id = ? and password = ?";

        jdbcTemplate.update(sql, id, request.getPassword());
    }

    private RowMapper<Schedule> mapScheduleRow() {
        return ((rs, rowNum) -> Schedule.builder()
                .id(rs.getInt("id"))
                .body(rs.getString("body"))
                .password(rs.getString("password"))
                .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                .updateAt(rs.getTimestamp("update_at").toLocalDateTime())
                .author(Author.builder()
                        .id(rs.getInt("author_id"))
                        .build())
                .build());
    }

    private RowMapper<Schedule> mapScheduleJoinAuthorRow() {
        return ((rs, rowNum) -> Schedule.builder()
                .id(rs.getInt("id"))
                .body(rs.getString("body"))
                .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                .updateAt(rs.getTimestamp("update_at").toLocalDateTime())
                .author(Author.builder()
                        .id(rs.getInt("b.id"))
                        .name(rs.getString("b.name"))
                        .email(rs.getString("b.e_mail"))
                        .createAt(rs.getTimestamp("author_create_at").toLocalDateTime())
                        .updateAt(rs.getTimestamp("author_update_at").toLocalDateTime())
                        .build())
                .build());
    }
}
