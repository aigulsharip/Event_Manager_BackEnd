package kz.daracademy.controller;

import kz.daracademy.feign.CommentFeign;
import kz.daracademy.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
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
        return commentFeign.createComment(comment);
    }

    @GetMapping()
    public Comment getCommentById(@RequestParam String commentId) {
        return commentFeign.getCommentById(commentId);
    }

    @PutMapping()
    public Comment updateComment(@RequestParam String commentId, @RequestBody Comment comment){
        comment.setCommentId(commentId);
        return commentFeign.updateComment(comment, commentId);
    }

    @DeleteMapping()
    public void deleteComment(@RequestParam String commentId){
        commentFeign.deleteComment(commentId);
    }

}
