package com.example.MockProject.ServiceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MockProject.Configuration.PasswordConfiguration;
import com.example.MockProject.Exceptions.ApiRequestException;
import com.example.MockProject.Model.Skill;
import com.example.MockProject.Model.User;
import com.example.MockProject.Model.VerifyToken;
import com.example.MockProject.Repository.SkillRepo;
import com.example.MockProject.Repository.UserRepo;
import com.example.MockProject.Repository.VerifyTokenRepo;
import com.example.MockProject.Service.SigninService;

@Service
public class SigninServiceImpl implements SigninService {
	
	  @Autowired
	 private UserRepo userRepo;
	  @Autowired
	 private SkillRepo sRepo;
	  @Autowired
	 private PasswordEncoder encoder;
	  @Autowired
	 private VerifyTokenRepo tokenRepo;
	  
	  public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
	    	
	    	if (userRepo.findByEmail(user.getEmail()) == null ) {
	    		String[] tag_temp = user.getTags().split(",");
	    		
	    		for(String i : tag_temp)
	    		{
	    			if(sRepo.findByTag(i) == null)
	    			{
	    				Skill skill = new Skill();
	    				skill.setTag(i);
	    				sRepo.save(skill);
	    			}
	    		}
	    		
	    		user.setPassword(encoder.encode(user.getPassword()));
	    		user.setEnabled(false);
	        	User result = userRepo.save(user);
	        	
	        	VerifyToken token = new VerifyToken(user);
	        	String verification_token = UUID.randomUUID().toString();
	        	LocalDateTime verify_issue_date = LocalDateTime.now();
	        	LocalDateTime verify_expire_date = verify_issue_date.plusDays(1);
	        	token.setVerification_token(verification_token);
	        	token.setVerify_issue_date(verify_issue_date);
	        	token.setVerify_expire_date(verify_expire_date);
	        	tokenRepo.save(token);
	        	
	        	try {
	        		String message ="Kudos on your first step of being professional!" + "Hope that you sooon get your first interview experience with us" + "Kindly check your details" +"\n"+user.getFirst_name()+user.getLast_name()+"\n"+user.getMobile()+"\n\n"+"To confirm your account, please click here : "+"\n"+"http://localhost:8080/api/confirm?token="+token.getVerification_token();
	        		String subject="WELCOME TO THE MOCK INTERVIEW PLATFORM SCHEDULER";
					NotificationServiceImpl.sendEmail(user.getEmail(), message, subject);
					} 
	        	
	        	catch (MailException mailException) {
					System.out.println(mailException);
					throw new ApiRequestException("This mail id is not valid. Please check your mail address.");
													}
	        	
	            return ResponseEntity.created(new URI("/api/users" + result.getId())).body(result);
	        }
	    	throw new ApiRequestException("This mail already exists. Try to login.");
	    }
}
