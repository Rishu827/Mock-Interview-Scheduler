package com.example.MockProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

	@Query(
            value = "SELECT users.tags FROM users WHERE users.email = :email",
            nativeQuery = true
    )
	List<?> findSkill(String email);
	
	@Query(
            value = "SELECT users.role FROM users WHERE users.email = :email",
            nativeQuery = true
    ) 
	int RoleId(String email); 
	
	@Query(
            value = "SELECT users.is_enabled FROM users WHERE users.email = :email",
            nativeQuery = true
    ) 
	boolean EnableStatus(String email); 
}
