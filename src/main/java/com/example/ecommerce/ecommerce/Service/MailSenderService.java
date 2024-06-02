package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.config.JavaMailsenderConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    JavaMailSender javaMailsender;

    public void send(String subject, String content, String to)throws MessagingException {
        MimeMessage mimeMessage = javaMailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setSubject(subject);
        helper.setText(content);
        helper.setTo(to);

        javaMailsender.send(mimeMessage);
    }
}
