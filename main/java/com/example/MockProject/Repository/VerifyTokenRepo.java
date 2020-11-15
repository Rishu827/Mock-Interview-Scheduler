package com.example.MockProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.VerifyToken;

public interface VerifyTokenRepo extends JpaRepository<VerifyToken, Long>{

    @Query(
            value = "SELECT * FROM verification WHERE verification.verification_token = :token",
            nativeQuery = true
    )
    VerifyToken findVerificationToken(String token);
	
}