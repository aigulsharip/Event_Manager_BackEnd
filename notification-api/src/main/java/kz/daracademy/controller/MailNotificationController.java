package kz.daracademy.controller;

import kz.daracademy.service.email.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@Slf4j
public class MailNotificationController {

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/check")
    public String check() {
        return "mail-notification is working";
    }

    @PostMapping("/send-email")
    public void sendEmail () {
        emailSenderService.sendEmail("aigul.sharip@nu.edu.kz", "This is subject", "Message");
    }

}
