package com.alek.influentialpeople.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServer implements EmailSender {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmail(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        message.setTo(email.getRecipient());
        emailSender.send(message);
    }
}