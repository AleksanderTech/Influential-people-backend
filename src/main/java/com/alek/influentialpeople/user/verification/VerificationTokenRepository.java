package com.alek.influentialpeople.user.verification;

import com.alek.influentialpeople.user.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
