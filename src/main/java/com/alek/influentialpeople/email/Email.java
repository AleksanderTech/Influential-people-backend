package com.alek.influentialpeople.email;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Email {

    private String recipient;
    private String from;
    private String subject;
    private String content;

    public Email(String recipient, String from, String subject, String content) {
        this.recipient = recipient;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }
}