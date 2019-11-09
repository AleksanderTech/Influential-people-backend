package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.common.Urls;
import com.alek.influentialpeople.email.Email;
import com.alek.influentialpeople.email.EmailSender;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserService;
import com.alek.influentialpeople.user.verification.entity.VerificationToken;
import com.alek.influentialpeople.user.verification.persistence.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    private Properties properties;
    private EmailSender emailSender;
    private UserService userService;
    private VerificationTokenRepository tokenRepository;

    public AuthService(Properties properties, EmailSender emailSender, UserService userService, VerificationTokenRepository tokenRepository) {
        this.properties = properties;
        this.emailSender = emailSender;
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(User user) {

        userService.createUser(user);
        VerificationToken token = tokenRepository.save(makeToken(user));

        emailSender.sendEmail(new Email(user.getEmail(), properties.getConfig("spring.mail.username"), properties.getConfig("email.verification.subject"), properties.getConfig("email.verification.message") + "\n\n" + makeConfirmationUrl(token.getValue())));

        return user;
    }

    private VerificationToken makeToken(User user) {

        String token = UUID.randomUUID().toString();
        return VerificationToken.builder().user(user).value(token).expireDate(new Date(new Date().toInstant().toEpochMilli() + VerificationToken.VALIDITY_TIME)).build();
    }

    private String makeConfirmationUrl(String token) {

        return Urls.ROOT_URL+ "/confirm?token=" + token;
    }

    public String confirm(String tokenValue) {

        VerificationToken verificationToken = tokenRepository.findByValue(tokenValue);
        if (verificationToken == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_VERIFICATION_TOKEN_MESSAGE);
        }
        User user = verificationToken.getUser();
        if (verificationToken.getExpireDate().after(new Date())) {
            user.setEnabled(true);
        }
        userService.saveUser(user);
        return properties.getConfig("trusted.origin");
    }
}
