package com.fram.springsecurityclient.repository;

import com.fram.springsecurityclient.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VerificationTokenRepository extends
        JpaRepository <VerificationToken, Long>{
    VerificationToken findByToken(String token);
}
