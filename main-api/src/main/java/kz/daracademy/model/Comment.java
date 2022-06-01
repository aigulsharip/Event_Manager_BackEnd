package kz.daracademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String commentId;
    private String text;
    private UserDetailsModel user;
    private String eventId;
    private String parentCommentId;
}
