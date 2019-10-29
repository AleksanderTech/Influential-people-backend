package com.alek.influentialpeople.user.verification.persistence;

import com.alek.influentialpeople.user.verification.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByValue(String value);
}
