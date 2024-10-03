package assignment.introductory.scheduleManagement.domain.schedule.repository;

import assignment.introductory.scheduleManagement.domain.schedule.Author;
import assignment.introductory.scheduleManagement.exception.customException.NotFoundSearchInfoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import static assignment.introductory.scheduleManagement.domain.schedule.dto.AddScheduleDTO.AuthorInfo;
import static assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode.NOT_FOUND_SCHEDULE;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author save(AuthorInfo requestInfo, LocalDateTime createAt) {
        String sql = "insert into author(name, e_mail, create_at, update_at) values (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, requestInfo.getName());
            ps.setString(2, requestInfo.getEmail());
            ps.setTimestamp(3, Timestamp.valueOf(createAt));
            ps.setTimestamp(4, Timestamp.valueOf(createAt));
            return ps;
        }, keyHolder);

        int key = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return Author.builder()
                .id(key)
                .name(requestInfo.getName())
                .email(requestInfo.getEmail())
                .createAt(createAt)
                .updateAt(createAt)
                .build();
    }

    @Override
    public Optional<Author> findByEmail(String email) {
        String sql = "select id, name, e_mail, create_at, update_at from author where e_mail = ?";

        try {
        Author foundAuthor = jdbcTemplate.queryForObject(sql, mapAuthorRow(), email);
            return Optional.ofNullable(foundAuthor);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Author findById(int id) {
        String sql = "select id, name, e_mail, create_at, update_at from author where id = ?";

        try {
            Author foundAuthor = jdbcTemplate.query(sql, mapAuthorRow(), id).get(0);
            if (foundAuthor == null) {
                throw new NullPointerException();
            }
            return foundAuthor;

        } catch (EmptyResultDataAccessException | NullPointerException e) {
            throw new NotFoundSearchInfoException(NOT_FOUND_SCHEDULE);
        }
    }

    @Override
    public void update(int id, String name, LocalDateTime updateAt) {
        String sql = "update author set name = ?, update_at = ? where id = ?";

        jdbcTemplate.update(sql, name, updateAt, id);
    }

    private RowMapper<Author> mapAuthorRow() {
        return ((rs, rowNum) -> Author.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("e_mail"))
                .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                .updateAt(rs.getTimestamp("update_at").toLocalDateTime())
                .build());
    }
}
