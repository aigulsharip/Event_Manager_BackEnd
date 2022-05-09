package kz.daracademy.commentapi.service;

import kz.daracademy.commentapi.model.CommentRequest;
import kz.daracademy.commentapi.model.CommentResponse;
import kz.daracademy.commentapi.repository.CommentEntity;
import kz.daracademy.commentapi.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        commentRequest.setCommentId(UUID.randomUUID().toString());
        CommentEntity commentEntity = modelMapper.map(commentRequest, CommentEntity.class);
        commentEntity = commentRepository.save(commentEntity);
        return modelMapper.map(commentEntity, CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(CommentRequest commentRequest) {
        CommentEntity commentEntity = modelMapper.map(commentRequest, CommentEntity.class);
        CommentEntity dbEntity = commentRepository.getCommentEntityByCommentId(commentRequest.getCommentId());
        commentEntity.setCommentId(dbEntity.getCommentId());
        commentEntity = commentRepository.save(commentEntity);
        return modelMapper.map(commentEntity, CommentResponse.class);
    }

    @Override
    public List<CommentResponse> getAllCommentsByEventId(String eventId) {
        return commentRepository.getCommentEntitiesByEventId(eventId).stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getAllCommentsByUserId(String userId) {
        return commentRepository.getCommentEntitiesByEventId(userId).stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getAllCommentsList() {
        return commentRepository.getCommentEntities().stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommentResponse> getAllCommentsPageable(Page page) {
        return null;
    }

    @Override
    public void deleteCommentById(String commentId) {

    }
}
