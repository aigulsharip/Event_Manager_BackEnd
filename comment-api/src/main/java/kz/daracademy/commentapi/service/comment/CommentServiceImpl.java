package kz.daracademy.commentapi.service.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.daracademy.commentapi.feign.EventFeign;
import kz.daracademy.commentapi.model.comment.CommentNotificationInfo;
import kz.daracademy.commentapi.model.comment.CommentRequest;
import kz.daracademy.commentapi.model.comment.CommentResponse;
import kz.daracademy.commentapi.model.event.EventResponse;
import kz.daracademy.commentapi.model.user.UserResponse;
import kz.daracademy.commentapi.repository.CommentEntity;
import kz.daracademy.commentapi.repository.CommentRepository;
import kz.daracademy.commentapi.service.message.SendService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventFeign eventFeign;


    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SendService sendService;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) throws JsonProcessingException {
        if (commentRequest.getParentCommentId() != null) {
            sendNotification(commentRequest);
        }
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
    public CommentResponse getCommentById(String commentId) {
        CommentEntity commentEntity = commentRepository.getCommentEntityByCommentId(commentId);
        return modelMapper.map(commentEntity, CommentResponse.class);
    }

    @Override
    public List<CommentResponse> getAllCommentsList() {
        return commentRepository.getCommentEntitiesBy().stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(String commentId) {
        commentRepository.deleteCommentEntityByCommentId(commentId);

    }

    @Override
    public List<CommentResponse> getCommentEntitiesByParentCommentIdIsNullAndAndEventId(String eventId) {
        return commentRepository.getCommentEntitiesByParentCommentIdIsNullAndAndEventId(eventId).stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getAllRepliesOfComment(String commentId) {
        return commentRepository.getCommentEntitiesByParentCommentId(commentId).stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getCommentsByEventId(String eventId) {
        return commentRepository.getCommentEntitiesByEventId(eventId).stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getListOfReplyComments() {
        return commentRepository.getCommentEntitiesByParentCommentIdNotNull().stream()
                .map(comment -> modelMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentNotificationInfo prepareCommentForNotification(String commentId) {
        CommentNotificationInfo commentNotificationInfo = new CommentNotificationInfo();
        CommentResponse comment = getCommentById(commentId);
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
            CommentResponse parentComment = getCommentById(parentCommentId);
            UserResponse parentCommentator = eventFeign.getUserById(parentComment.getUserId());
            String parentCommentatorName = parentCommentator.getFullName();
            String parentCommentatorEmail = parentCommentator.getEmail();
            String parentCommentatorText = getCommentById(parentCommentId).getText();
            commentNotificationInfo = new CommentNotificationInfo(commentatorName, commentatorEmail, commentatorText, eventTitle, parentCommentatorName, parentCommentatorEmail, parentCommentatorText);

        }
        return commentNotificationInfo;
    }

    public String sendNotification(CommentRequest commentRequest) throws JsonProcessingException {
        List<CommentResponse> listByEvent = getAllCommentsByEventId(commentRequest.getEventId());
        for (CommentResponse cr : listByEvent) {
            if (cr.getCommentId().equals(commentRequest.getParentCommentId())) {
                cr.getUserId();
                sendService.send(objectMapper.writeValueAsString(cr));
            }
        }

        return "InFO";
    }

    ;

}
