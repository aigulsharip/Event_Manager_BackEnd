package kz.daracademy.commentapi.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CommentNotificationInfo {



    private String commentatorName;
    private String commentatorEmail;
    private String commentatorText;
    private String eventTitle;
    private String parentCommentatorName;
    private String parentCommentatorEmail;
    private String parentCommentatorText;


    public CommentNotificationInfo(String commentatorName, String commentatorEmail, String commentatorText, String eventTitle) {
        this.commentatorName = commentatorName;
        this.commentatorEmail = commentatorEmail;
        this.commentatorText = commentatorText;
        this.eventTitle = eventTitle;
    }


}
