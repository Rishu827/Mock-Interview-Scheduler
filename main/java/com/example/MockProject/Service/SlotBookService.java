package com.example.MockProject.Service;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MockProject.Model.Interviewee;
import com.example.MockProject.Model.Interviewer;

public interface SlotBookService {

	public ResponseEntity<Interviewer> createInterviewer(@Valid @RequestBody Interviewer interviewer) throws URISyntaxException;
	
	public ResponseEntity<Interviewee> createInterviewee(@Valid @RequestBody Interviewee interviewee) throws URISyntaxException;
}
