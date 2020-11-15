package com.example.MockProject.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:3000")
public class PasswordConfiguration {

	@Bean
    public PasswordEncoder passwordEncoder() 
	{
        return new BCryptPasswordEncoder(12);
    }
}
