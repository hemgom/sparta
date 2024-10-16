package assigment.mastery.scheduleManagementJPA.domain.comment.service;

import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.AddComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseCommentList;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.UpdateComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.repository.CommentRepository;
import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.repository.MemberRepository;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.repository.ScheduleRepository;
import assigment.mastery.scheduleManagementJPA.exception.customException.HasNotPermissionException;
import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseComment save(Long scheduleId, Member member, AddComment request) {
        Schedule found = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_SCHEDULE));

        Comment created = Comment.create(found, member.getId(), request);
        Comment saved = commentRepository.save(created);

        return Comment.makeResponse(saved, member.getName());
    }

    @Transactional(readOnly = true)
    public ResponseComment findById(Long commentId) {
        Comment found = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_COMMENT));

        Member author = memberRepository.findById(found.getAuthorId())
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

        return Comment.makeResponse(found, author.getName());
    }

    @Transactional(readOnly = true)
    public ResponseCommentList findAll(String author) {
        List<Member> foundMembers = memberRepository.findAllByName(author);

        Map<Long, String> authorsIdAndName = new HashMap<>();
        foundMembers.forEach(member -> authorsIdAndName.put(member.getId(), member.getName()));
        Set<Long> authorIds = authorsIdAndName.keySet();

        List<Comment> foundComments = commentRepository.findAllByAuthor(authorIds);

        List<ResponseComment> responseComments = new ArrayList<>();
        foundComments.forEach(comment -> responseComments.add(Comment.makeResponse(comment, authorsIdAndName.get(comment.getAuthorId()))));

        return ResponseCommentList.builder()
                .comments(responseComments)
                .build();
    }

    @Transactional
    public void update(Long commentId, Member member, UpdateComment request) {
        Comment found = commentRepository.findById(commentId)
                        .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_COMMENT));

        checkAuthor(found.getAuthorId(), member.getId());

        found.update(request);
        commentRepository.save(found);
    }

    @Transactional
    public void delete(Long commentId, Member member) {
        Comment found = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_COMMENT));

        checkAuthor(found.getAuthorId(), member.getId());

        commentRepository.delete(found);
    }

    private void checkAuthor(Long commentAuthorId, Long requesterId) {
        if (!commentAuthorId.equals(requesterId))
            throw new HasNotPermissionException(HAS_NOT_PERMISSION);
    }
}
