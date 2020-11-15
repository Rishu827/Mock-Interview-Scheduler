package com.example.MockProject.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "interview")
public class Interview {
	
	 public Interview() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String tag;
	
	@Temporal(TemporalType.DATE)
    private Date date;
	
	@Email
    private String interviewerId;
	
	@Email
    private String intervieweeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(String interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getIntervieweeId() {
		return intervieweeId;
	}

	public void setIntervieweeId(String intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	
}
