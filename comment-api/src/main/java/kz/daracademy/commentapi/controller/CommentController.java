package kz.daracademy.commentapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.daracademy.commentapi.feign.EventFeign;
import kz.daracademy.commentapi.model.comment.CommentNotificationInfo;
import kz.daracademy.commentapi.model.comment.CommentRequest;
import kz.daracademy.commentapi.model.comment.CommentResponse;
import kz.daracademy.commentapi.service.comment.CommentService;
import kz.daracademy.commentapi.service.message.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private EventFeign eventFeign;

    @Autowired
    SendService sendService;

    ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/check/data")
    public String chech() {
        return eventFeign.check();
    }


    @GetMapping("/check")
    public String check() {
        return "comment-api is working";
    }

    @PostMapping
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest) throws JsonProcessingException {
        return commentService.createComment(commentRequest);
    }

    @PutMapping
    public CommentResponse updateComment(@RequestParam String commentId, @RequestBody CommentRequest commentRequest) {
        commentRequest.setCommentId(commentId);
        return commentService.updateComment(commentRequest);
    }

    @GetMapping
    public CommentResponse getCommentById(@RequestParam String commentId) {
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/all")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllCommentsList();
    }

    @DeleteMapping
    public void deleteComment(@RequestParam String commentId) {
        commentService.deleteCommentById(commentId);
    }

    @GetMapping("/event")
    public List<CommentResponse> getCommentsByEventIds(@RequestParam String eventId) {
        return commentService.getCommentsByEventId(eventId);
    }


    @GetMapping("/detail")
    public List<CommentResponse> getAllReplies(@RequestParam String commentId) {
        return commentService.getAllRepliesOfComment(commentId);
    }





    @GetMapping("/reply")
    public List<CommentResponse> getReplyComments() {
        return commentService.getListOfReplyComments();
    }


    @PostMapping("/email")
    public ResponseEntity<String> sendCommentData(@RequestParam String commentId) throws JsonProcessingException {
        CommentNotificationInfo commentNotificationInfo = commentService.prepareCommentForNotification(commentId);
        if (commentNotificationInfo.getParentCommentatorName() == null) {
            return new ResponseEntity<>("This is main comment and no notificaton will be sent", HttpStatus.OK);
        }
        sendService.send(objectMapper.writeValueAsString(commentNotificationInfo));
        return new ResponseEntity<>("Mail Send Succesfully", HttpStatus.OK);
    }


}
