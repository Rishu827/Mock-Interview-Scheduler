package com.example.MockProject.Service;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MockProject.Model.User;

public interface SigninService {
	
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException; 

}
