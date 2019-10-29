package com.alek.influentialpeople.user.verification.entity;

import com.alek.influentialpeople.user.entity.User;
import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

    public static final int VALIDITY_TIME = 24 * 60 * 60 * 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username", referencedColumnName = "username")
    private User user;

    private Date expireDate;
}
