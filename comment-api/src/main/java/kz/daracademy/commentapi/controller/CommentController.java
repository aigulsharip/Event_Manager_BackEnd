package kz.daracademy.commentapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import kz.daracademy.commentapi.feign.EventFeign;
import kz.daracademy.commentapi.model.CommentRequest;
import kz.daracademy.commentapi.model.CommentResponse;
import kz.daracademy.commentapi.service.CommentService;
import kz.daracademy.commentapi.service.message.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private  CommentService commentService;

    @Autowired
    private EventFeign eventFeign;

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

    @GetMapping("/event")
    public List<CommentResponse> getCommentsByEventId(@RequestParam String eventId){
        return commentService.getCommentEntitiesByParentCommentIdIsNullAndAndEventId(eventId);
    }

    @GetMapping("/all")
    public List<CommentResponse> getAllComments(){
        return commentService.getAllCommentsList();
    }

    @DeleteMapping
    public void deleteComment(@RequestParam String commentId){
        commentService.deleteCommentById(commentId);
    }

    @GetMapping("/detail")
    public List<CommentResponse> getAllReplies(@RequestParam String commentId){
        return commentService.getAllRepliesOfComment(commentId);
    }



}
