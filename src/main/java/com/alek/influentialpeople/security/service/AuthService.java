package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.common.ConvertersFactory;
import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.email.Email;
import com.alek.influentialpeople.email.EmailSender;
import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserAccount;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.service.UserService;
import com.alek.influentialpeople.user.verification.entity.VerificationToken;
import com.alek.influentialpeople.user.verification.persistence.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    private UserService userService;
    private VerificationTokenRepository tokenRepository;
    private EmailSender emailSender;
    private Properties properties;

    public AuthService(UserService userService, VerificationTokenRepository tokenRepository, EmailSender emailSender, Properties properties) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.emailSender = emailSender;
        this.properties = properties;
    }

    public UserResponse signUp(UserRegistration userRegistration) {

        User user = ConvertersFactory.<UserRegistration, User>getConverter(ConvertersFactory.ConverterType.USER_REGISTRATION_TO_USER).convert(userRegistration);
        userService.createUser(ConvertersFactory.<User, UserAccount>getConverter(ConvertersFactory.ConverterType.USER_TO_USER_ACCOUNT).convert(user), true);
        tokenRepository.save(makeToken(user));
        emailSender.sendEmail(new Email(userRegistration.getEmail(), properties.getConfig("spring.mail.username"), properties.getConfig("email.verification.subject"), properties.getConfig("email.verification.message")));
        return ConvertersFactory.<User, UserResponse>getConverter(ConvertersFactory.ConverterType.USER_TO_USER_RESPONSE).convert(user);
    }

    private VerificationToken makeToken(User user) {

        String token = UUID.randomUUID().toString();
        return VerificationToken.builder().user(user).value(token).expireDate(new Date(new Date().toInstant().toEpochMilli() + VerificationToken.VALIDITY_TIME)).build();
    }

    public void confirm() {

    }
}
