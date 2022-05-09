package kz.daracademy.commentapi.service;

import kz.daracademy.commentapi.model.CommentRequest;
import kz.daracademy.commentapi.model.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentService  {

    //create
    CommentResponse createComment(CommentRequest commentRequest);

    //update
    CommentResponse updateComment(CommentRequest commentRequest);


    //Get Comments by Event
    List<CommentResponse> getAllCommentsByEventId(String eventId);

    //Get Comments by User
    List<CommentResponse> getAllCommentsByUserId(String userId);

    //Get ALL comments
    List<CommentResponse> getAllCommentsList();
    Page<CommentResponse> getAllCommentsPageable(Page page);

    //Delete
    void deleteCommentById(String commentId);
}
