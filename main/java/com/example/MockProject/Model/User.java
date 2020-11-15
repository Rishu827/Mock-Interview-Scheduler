package com.example.MockProject.Model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
	 @GeneratedValue
	private Long id;
	
	 @NotNull(message = "First name can not be null")
	 @Size(min = 2, message = "First name can not be less than 2 characters")
	private String first_name;
	
	private String last_name;
	
	 @Email(message = "Email is not formatted correctly")
	private String email;
	
	private String gender;
	
	private String mobile;
	
	private String password;

	private String tags;
	
	
	private int role;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

   
	
	public User() {
		super();
		this.role = 1;
		this.isAccountNonExpired = true;
		this.isAccountNonLocked = true;
		this.isCredentialsNonExpired = true;
		this.isEnabled = true;
	}


	public User(boolean isAccountNonExpired,
				boolean isAccountNonLocked, 
				boolean isCredentialsNonExpired, 
				boolean isEnabled) {
		super();
		this.isAccountNonExpired = true;
		this.isAccountNonLocked = true;
		this.isCredentialsNonExpired = true;
		this.isEnabled = true;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		switch (role)
		{
		case 0 : return com.example.MockProject.Roles_Permission.UserRole.ADMIN.getGrantedAuthorities();		
		
		case 1 : return com.example.MockProject.Roles_Permission.UserRole.USER.getGrantedAuthorities();
		}
		return null;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	@Override
	public String getUsername() {
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}


	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}


	@Override
	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
