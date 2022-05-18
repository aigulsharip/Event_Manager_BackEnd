package kz.daracademy.commentapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.daracademy.commentapi.model.CommentRequest;
import kz.daracademy.commentapi.model.CommentResponse;
import kz.daracademy.commentapi.repository.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentService  {

    //create
    CommentResponse createComment(CommentRequest commentRequest) throws JsonProcessingException;

    //update
    CommentResponse updateComment(CommentRequest commentRequest);


    //Get Comments by Event
    List<CommentResponse> getAllCommentsByEventId(String eventId);

    //Get Comments by User
    List<CommentResponse> getAllCommentsByUserId(String userId);

    //Get Comment by ID
    CommentResponse getCommentById(String commentId);
    //Get ALL comments
    List<CommentResponse> getAllCommentsList();
//    Page<CommentResponse> getAllCommentsPageable(Pageable page);

    //Delete
    void deleteCommentById(String commentId);

    List<CommentResponse> getCommentEntitiesByParentCommentIdIsNullAndAndEventId(String eventId);

    List<CommentResponse> getAllRepliesOfComment(String commentId);
}
