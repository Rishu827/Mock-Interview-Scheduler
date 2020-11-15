package com.example.MockProject.Controller;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockProject.ServiceImpl.LoginServiceImpl;
import com.example.MockProject.ServiceImpl.SigninServiceImpl;
import com.example.MockProject.Model.User;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	  @Autowired
	 private LoginServiceImpl login;
	  @Autowired
	 private SigninServiceImpl signin;
		
	    @PostMapping("/signup")
	    @ResponseBody
	    ResponseEntity<User> signIn(@Valid @RequestBody User user) throws URISyntaxException {
	    	
	    	return signin.createUser(user);
	    	
	    }
	    
	    @PostMapping("/login")
	    
	    public ResponseEntity<User> logIn(@Valid @RequestBody User user) throws URISyntaxException {
	    	
	    	return login.verifyUser(user);
	    }
}
