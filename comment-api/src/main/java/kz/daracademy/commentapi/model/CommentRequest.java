package kz.daracademy.commentapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String commentId;
    private String text;
    private String userId;
    private String eventId;
    private String parentCommentId;
}
