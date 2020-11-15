package com.example.MockProject.JWT;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.MockProject.Exceptions.ApiRequestException;
import com.example.MockProject.Model.User;
import com.example.MockProject.ServiceImpl.LoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@CrossOrigin(origins = "http://localhost:3000")
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

private final AuthenticationManager authenticationManager;

private LoginServiceImpl loginServiceImpl;
	
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, LoginServiceImpl loginServiceImpl) {
		this.authenticationManager = authenticationManager;
		this.loginServiceImpl = loginServiceImpl;
	}
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            User authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), User.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            );
            String email = authenticationRequest.getEmail();
            if(!loginServiceImpl.Enable(email))
            {
            	throw new ApiRequestException("Account not Verified");
            }
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;
        } 
        catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
	                                            HttpServletResponse response,
	                                            FilterChain chain,
	                                            Authentication authResult) throws IOException, ServletException {
	                
		String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

		
        Collection<? extends GrantedAuthority> auth = null;
		try
		{
				String email = authResult.getName();
		        int role = 0;
		        role = loginServiceImpl.RoleNumber(email);
		        switch(role)
		        {
		        case 0 : auth = com.example.MockProject.Roles_Permission.UserRole.ADMIN.getGrantedAuthorities();
		        		break;
		        
		        case 1 : auth = com.example.MockProject.Roles_Permission.UserRole.USER.getGrantedAuthorities();
						break;
		        }
		}

		catch (Exception e)
		{
			System.out.println(e);
		}

		String token = Jwts.builder()
				.setSubject(authResult.getName())
					//.claim("authorities", authResult.getAuthorities())
					.claim("authorities", auth)
	                .setIssuedAt(new Date())
	                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
	                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
	                .compact();

	        response.addHeader("Authorization", "Bearer "+ token);
	        System.out.println(token);
	        
	        //return ResponseEntity.ok().body(token);
	 }
}
