package com.example.MockProject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MockProject.Exceptions.ApiRequestException;
import com.example.MockProject.Model.User;
import com.example.MockProject.Repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);

		if(user == null)
    		throw new ApiRequestException("Email address doesnot exist!!");
		return new UserDetailsImpl(user); 
	}

}
