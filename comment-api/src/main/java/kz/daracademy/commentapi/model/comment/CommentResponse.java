package kz.daracademy.commentapi.model.comment;

import kz.daracademy.commentapi.model.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String commentId;
    private String text;
    private UserResponse user;
    private String eventId;
    private String parentCommentId;
}
