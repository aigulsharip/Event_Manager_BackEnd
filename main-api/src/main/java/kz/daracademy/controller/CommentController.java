package kz.daracademy.controller;

import kz.daracademy.feign.CommentFeign;
import kz.daracademy.model.Comment;
import kz.daracademy.model.UserDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentFeign commentFeign;

    @GetMapping("/check")
    public String checkComment() {
        return commentFeign.chech();
    }

    @GetMapping("/all")
    public List<Comment> getALlComments(){
        return commentFeign.getAllComments();
    }

    @PostMapping()
    public Comment createComment(@RequestBody Comment comment){
        UserDetailsModel principal = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(principal);
        return commentFeign.createComment(comment);
    }

    @GetMapping()
    public Comment getCommentById(@RequestParam String commentId) {
        return commentFeign.getCommentById(commentId);
    }

    @PutMapping()
    public Comment updateComment(@RequestParam String commentId, @RequestBody Comment comment){
        comment.setCommentId(commentId);
        UserDetailsModel principal = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(principal);
        return commentFeign.updateComment(comment, commentId);
    }

    @DeleteMapping()
    public void deleteComment(@RequestParam String commentId){
        commentFeign.deleteComment(commentId);
    }

    @GetMapping("/event")
    public List<Comment> getCommentsByEventIds(@RequestParam String eventId){
        return commentFeign.getCommentsByEventIds(eventId);
    }


    @GetMapping("/detail")
    public List<Comment> getAllReplies(@RequestParam String commentId) {
        return commentFeign.getAllReplies(commentId);
    }

}
