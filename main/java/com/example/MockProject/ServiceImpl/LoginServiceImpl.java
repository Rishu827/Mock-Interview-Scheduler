package com.example.MockProject.ServiceImpl;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MockProject.Exceptions.ApiRequestException;
import com.example.MockProject.Model.User;
import com.example.MockProject.Repository.UserRepo;
import com.example.MockProject.Service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	 @Autowired
	 private UserRepo userRepo;

	public ResponseEntity<User> verifyUser(@Valid @RequestBody User user) throws URISyntaxException {
    	
    	if (userRepo.findByEmail(user.getEmail()) == null) 
    		throw new ApiRequestException("Email address doesnot exist!!");
    	
    	User exist_user = new User();
        exist_user = userRepo.findByEmail(user.getEmail()); 
        if (exist_user.getPassword().equals(user.getPassword()))
        {
        	User user_login = userRepo.findByEmail(user.getEmail());
        	return ResponseEntity.ok().body(user_login);	        		
        }
        throw new ApiRequestException("Incorrect Password");
    }
	
	public int RoleNumber(@Valid @RequestBody String email) throws URISyntaxException {
		return userRepo.RoleId(email);
	}
	
	public boolean Enable(@Valid @RequestBody String email) throws URISyntaxException {
		return userRepo.EnableStatus(email);
	}
}