package com.example.MockProject.Controller;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockProject.Exceptions.ApiRequestException;
import com.example.MockProject.Model.User;
import com.example.MockProject.Model.VerifyToken;
import com.example.MockProject.Repository.UserRepo;
import com.example.MockProject.Repository.VerifyTokenRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class VrifyTokenController {

	@Autowired
	 private UserRepo userRepo;
	@Autowired
	 private VerifyTokenRepo tokenRepo;

	@RequestMapping(value="/confirm", method= {RequestMethod.GET, RequestMethod.POST})
		 public ResponseEntity<User> confirmation(@Valid @RequestParam("token")String verificationToken) throws URISyntaxException{
	    VerifyToken token = tokenRepo.findVerificationToken(verificationToken);
	    LocalDateTime verificationTime = LocalDateTime.now();
	    
	    //System.out.println(token);
	    if((token != null) && (verificationTime.isBefore(token.getVerify_expire_date())))
	    {
	        User user = userRepo.findByEmail(token.getUser().getEmail());
	        user.setEnabled(true);
	        userRepo.save(user);
	        token.setVerification_token(null);
	        tokenRepo.save(token);
	        return ResponseEntity.ok().body(user);	
	    }
	    throw new ApiRequestException("Invalid link");
	    }
}
