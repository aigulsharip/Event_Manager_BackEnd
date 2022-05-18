package kz.daracademy.service.email;

import kz.daracademy.model.EmailMessage;
import kz.daracademy.model.EventNotificationInfo;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
    EmailMessage renderEventEmail (EventNotificationInfo eventNotificationInfo);
}
