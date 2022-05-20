package kz.daracademy.service.message;


import kz.daracademy.model.CommentNotificationInfo;
import kz.daracademy.model.EventNotificationInfo;

public interface ConsumeService {

    //void consumeEventInfo(EventNotificationInfo eventNotificationInfo);

    void consumeCommentInfo(CommentNotificationInfo commentNotificationInfo);

}
