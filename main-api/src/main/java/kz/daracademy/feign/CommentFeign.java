package kz.daracademy.feign;

import kz.daracademy.model.Comment;
import kz.daracademy.model.UserDetailsModel;
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

    @PostMapping("/user")
    void createUser(@RequestBody UserDetailsModel user);

    @GetMapping("/comment/event")
    List<Comment> getCommentsByEventIds(@RequestParam String eventId);


    @GetMapping("/comment/detail")
    List<Comment> getAllReplies(@RequestParam String commentId);





}
