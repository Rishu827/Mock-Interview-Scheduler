package com.example.MockProject.Model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "verification")
public class VerifyToken {
	
	public VerifyToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	private long tokenid;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "email")
	private User user;
	
	private String verification_token;
	private LocalDateTime verify_issue_date;
	private LocalDateTime verify_expire_date;
	
	public VerifyToken(User user) {
		super();
		this.user = user;
	}

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVerification_token() {
		return verification_token;
	}

	public void setVerification_token(String verification_token) {
		this.verification_token = verification_token;
	}

	public LocalDateTime getVerify_issue_date() {
		return verify_issue_date;
	}

	public void setVerify_issue_date(LocalDateTime verify_issue_date) {
		this.verify_issue_date = verify_issue_date;
	}

	public LocalDateTime getVerify_expire_date() {
		return verify_expire_date;
	}

	public void setVerify_expire_date(LocalDateTime verify_expire_date) {
		this.verify_expire_date = verify_expire_date;
	}
}
