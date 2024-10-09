package assigment.mastery.scheduleManagementJPA.domain.comment.service;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.AddComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseCommentList;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.UpdateComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.repository.CommentRepository;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_COMMENT;
import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.NOT_FOUND_SCHEDULE;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public ResponseComment save(long scheduleId, AddComment request) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        Comment created = Comment.create(request, found);

        Comment saved = commentRepository.save(created);

        return Comment.makeResponse(saved);
    }

    public ResponseComment findById(long commentId) {
        Comment found = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_COMMENT));

        return Comment.makeResponse(found);
    }

    public ResponseCommentList findAll(String author) {
        List<Comment> foundList = commentRepository.findAll(author);

        List<ResponseComment> responseCommentList = new ArrayList<>();
        foundList.stream().map(Comment::makeResponse).forEach(responseCommentList::add);

        return ResponseCommentList.builder()
                .comments(responseCommentList)
                .build();
    }

    @Transactional
    public void update(long commentId, UpdateComment request) {
        Comment found = commentRepository.findById(commentId)
                        .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_COMMENT));

        commentRepository.update(found, request);
    }

    public void delete(long commentId) {
        Comment found = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_COMMENT));

        commentRepository.deleteById(found.getId());
    }
}
