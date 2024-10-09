package assigment.mastery.scheduleManagementJPA.domain.comment.controller;

import assigment.mastery.scheduleManagementJPA.domain.comment.dto.AddComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseCommentList;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.UpdateComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseComment addComment(@PathVariable(name = "scheduleId") long scheduleId,
                                      @RequestBody @Valid AddComment request) {
        return commentService.save(scheduleId, request);
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseComment findCommentById(@PathVariable(name = "commentId") long commentId) {
        return commentService.findById(commentId);
    }

    @GetMapping("/search-condition")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCommentList findAllComments(@RequestParam(name = "author", defaultValue = "") String author) {
        return commentService.findAll(author);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable(name = "commentId") long commentId,
                              @RequestBody @Valid UpdateComment request) {
        commentService.update(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(name = "commentId") long commentId) {
        commentService.delete(commentId);
    }
}
