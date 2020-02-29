package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.email.Email;
import com.alek.influentialpeople.email.EmailSender;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserCrudRepository;
import com.alek.influentialpeople.user.verification.entity.VerificationToken;
import com.alek.influentialpeople.user.verification.persistence.VerificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.UUID;

@Service
public class RegisterService implements RegisterManager<User> {

    private final Properties properties;
    private final PasswordEncoder encoder;
    private final EmailSender emailSender;
    private final UserCrudRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    public RegisterService(Properties properties, PasswordEncoder encoder, EmailSender emailSender, UserCrudRepository userRepository, VerificationTokenRepository tokenRepository) {
        this.properties = properties;
        this.encoder = encoder;
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(User user) {
        create(user);
        VerificationToken token = tokenRepository.save(makeToken(user));
        emailSender.sendEmail(new Email(user.getEmail(), properties.getConfig("spring.mail.username"), properties.getConfig("email.verification.subject"), properties.getConfig("email.verification.message") + "\n\n" + properties.getConfig("gui.url.confirmation-token") + token.getValue()));
        return user;
    }

    @Override
    public String confirm(String tokenValue) {
        VerificationToken verificationToken = tokenRepository.findByValue(tokenValue);
        if (verificationToken == null) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_VERIFICATION_TOKEN_MESSAGE);
        }
        User user = verificationToken.getUser();
        if (verificationToken.getExpireDate().after(new Date())) {
            user.setEnabled(true);
        }
        save(user);
        return properties.getConfig("gui.url.sign-in-activated");
    }

    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException(ExceptionMessages.USER_EXISTS_MESSAGE);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    private VerificationToken makeToken(User user) {
        String token = UUID.randomUUID().toString();
        return VerificationToken.builder().user(user).value(token).expireDate(new Date(new Date().toInstant().toEpochMilli() + VerificationToken.VALIDITY_TIME)).build();
    }
}
