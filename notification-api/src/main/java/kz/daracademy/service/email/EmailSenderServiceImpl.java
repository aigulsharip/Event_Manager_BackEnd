package kz.daracademy.service.email;

import kz.daracademy.model.CommentNotificationInfo;
import kz.daracademy.model.EmailMessage;
import kz.daracademy.model.EventNotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("dareventmanager@gmail.com");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
        System.out.println("Mail Sent Succesfully");

    }

    @Override
    public EmailMessage renderEventEmail(EventNotificationInfo eventNotificationInfo) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(eventNotificationInfo.getEmail());
        emailMessage.setSubject("Уведомление: вы создали новое мероприятие");
        String message = "";
        message += "Добрый день, " + eventNotificationInfo.getName() + "! \n \n";
        message += "Вы создали новое мероприятие с названием " + eventNotificationInfo.getTitle() + ". \nСобытие опубликовано в " + eventNotificationInfo.getPostedDate() + " . \nМероприятие состоится в " + eventNotificationInfo.getStartDateTime() + ". ";
        emailMessage.setMessage(message);

        return emailMessage;
    }

    @Override
    public EmailMessage renderСommentEmail(CommentNotificationInfo commentNotificationInfo) {
        EmailMessage emailCommentMessage = new EmailMessage();
        emailCommentMessage.setSubject("Новый комментарий к мероприятию " + commentNotificationInfo.getEventTitle());
        String message = "";
        if (commentNotificationInfo.getParentCommentatorName() == null) {
            emailCommentMessage.setTo(commentNotificationInfo.getCommentatorEmail());

            message += "Пользователь " + commentNotificationInfo.getCommentatorName() + " написал комментарий. \n";
            message += "Комментарий: " + commentNotificationInfo.getCommentatorText();

        }
        else {
            emailCommentMessage.setTo(commentNotificationInfo.getParentCommentatorEmail());

            message += "Пользователь " + commentNotificationInfo.getCommentatorName() + " ответил на комментарий пользвателя " +
                    commentNotificationInfo.getParentCommentatorName() + " \n";
            message += "Ответный комментарий: " + commentNotificationInfo.getCommentatorText() + " \n";
            message += "Основной Комментарий: " + commentNotificationInfo.getParentCommentatorText() + "\n";


        }


        emailCommentMessage.setMessage(message);

        return emailCommentMessage;
    }


}
