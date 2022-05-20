package kz.daracademy.commentapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.daracademy.commentapi.feign.EventFeign;
import kz.daracademy.commentapi.model.comment.CommentNotificationInfo;
import kz.daracademy.commentapi.model.comment.CommentRequest;
import kz.daracademy.commentapi.model.comment.CommentResponse;
import kz.daracademy.commentapi.model.event.EventResponse;
import kz.daracademy.commentapi.model.user.UserResponse;
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
    private  CommentService commentService;

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
    public CommentResponse updateComment(@RequestParam String commentId, @RequestBody CommentRequest commentRequest){
        commentRequest.setCommentId(commentId);
        return commentService.updateComment(commentRequest);
    }

    @GetMapping
    public CommentResponse getCommentById(@RequestParam String commentId){
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/all")
    public List<CommentResponse> getAllComments(){
        return commentService.getAllCommentsList();
    }

    @DeleteMapping
    public void deleteComment(@RequestParam String commentId){
        commentService.deleteCommentById(commentId);
    }

    @GetMapping("/event")
    public List<CommentResponse> getCommentsByEventIds(@RequestParam String eventId){
        return commentService.getCommentsByEventId(eventId);
    }


    @GetMapping("/detail")
    public List<CommentResponse> getAllReplies(@RequestParam String commentId){
        return commentService.getAllRepliesOfComment(commentId);
    }



    @GetMapping("/event/parent-null")
    public List<CommentResponse> getCommentsByEventId(@RequestParam String eventId){
        return commentService.getCommentEntitiesByParentCommentIdIsNullAndAndEventId(eventId);
    }


    @GetMapping("/reply")
    public List<CommentResponse> getReplyComments(){
        return commentService.getListOfReplyComments();
    }



    @GetMapping("/email")
    public ResponseEntity<String> sendCommntData(@RequestParam String commentId) throws JsonProcessingException {
        CommentNotificationInfo commentNotificationInfo = new CommentNotificationInfo();
        CommentResponse comment = commentService.getCommentById(commentId);
        UserResponse commentator = eventFeign.getUserById(comment.getUserId());
        String commentatorName = commentator.getFullName();
        String commentatorEmail = commentator.getEmail();
        String commentatorText = comment.getText();
        EventResponse event = eventFeign.getEventById(comment.getEventId());
        String eventTitle = event.getTitle();
        if (comment.getParentCommentId() == null) {
            commentNotificationInfo = new CommentNotificationInfo(commentatorName, commentatorEmail, commentatorText, eventTitle);
        } else {
            String parentCommentId = comment.getParentCommentId();
            CommentResponse parentComment = commentService.getCommentById(parentCommentId);


            UserResponse parentCommentator = eventFeign.getUserById(parentComment.getUserId());
            String parentCommentatorName = parentCommentator.getFullName();
            String parentCommentatorEmail = parentCommentator.getEmail();
            String parentCommentatorText = commentService.getCommentById(parentCommentId).getText();
            commentNotificationInfo = new CommentNotificationInfo(commentatorName, commentatorEmail, commentatorText, eventTitle, parentCommentatorName, parentCommentatorEmail, parentCommentatorText);

        }
        System.out.println(commentNotificationInfo);


        sendService.send(objectMapper.writeValueAsString(commentNotificationInfo));
        return new ResponseEntity<>("Mail Send Succesfully", HttpStatus.OK);
    }











}
