package kz.daracademy.feign;

import kz.daracademy.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("comment-api")
public interface CommentFeign {

    @GetMapping("/comment/check")
    String chech();

    @GetMapping("/comment/all")
    List<Comment> getAllComments();

    @PostMapping("/comment")
    Comment createComment(@RequestBody Comment comment);

    @GetMapping("/comment")
    Comment getCommentById(@RequestParam String commentId);

    @PutMapping("/comment")
    Comment updateComment(@RequestBody Comment comment, @RequestParam String commentId);

    @DeleteMapping("/comment")
    void deleteComment(@RequestParam String commentId);



}
