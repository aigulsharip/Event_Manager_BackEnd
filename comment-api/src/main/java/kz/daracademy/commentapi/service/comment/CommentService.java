package kz.daracademy.commentapi.service.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.daracademy.commentapi.model.comment.CommentRequest;
import kz.daracademy.commentapi.model.comment.CommentResponse;

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

    List<CommentResponse> getCommentsByEventId(String eventId);

    List<CommentResponse> getListOfReplyComments();
}
