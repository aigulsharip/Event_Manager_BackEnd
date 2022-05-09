package kz.daracademy.commentapi.repository;


import kz.daracademy.commentapi.model.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends ElasticsearchRepository<CommentEntity, String> {

    //Get All Pageable
    Page<CommentEntity> getCommentEntitiesBy(Pageable pageable);

    List<CommentEntity> getCommentEntities();


    //Get by ID
    CommentEntity getCommentEntityByCommentId(String commentId);

    //Get By User Id
    Page<CommentEntity> getCommentEntitiesByUserId(String userId, Pageable page);
    List<CommentEntity> getCommentEntitiesByUserId(String userId);


    //Get By Event Id
    Page<CommentEntity> getCommentEntitiesByEventId(String eventId, Pageable page);
    List<CommentEntity> getCommentEntitiesByEventId(String eventId);

    //Delete
    CommentEntity deleteCommentEntityByCommentId(String commentId);



}
