package kz.daracademy.service.message;

import kz.daracademy.model.CommentNotificationInfo;
import kz.daracademy.model.EmailMessage;
import kz.daracademy.model.EventNotificationInfo;
import kz.daracademy.service.email.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumeServiceImpl implements ConsumeService {

    @Autowired
    EmailSenderService emailSenderService;

    /*
    @Override
    @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.in}",
            containerFactory = "singleFactory")


    public void consumeEventInfo(EventNotificationInfo eventNotificationInfo) {
        EmailMessage emailMessage = emailSenderService.renderEventEmail(eventNotificationInfo);
        emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        log.info("Message: {} successfully consumed", eventNotificationInfo);


    }

     */

    @Override
    @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic.in}",
            containerFactory = "singleFactory")
    public void consumeCommentInfo(CommentNotificationInfo commentNotificationInfo) {
        EmailMessage emailMessage = emailSenderService.renderСommentEmail(commentNotificationInfo);

        emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        log.info("Message: {} successfully consumed", commentNotificationInfo);

    }


}
