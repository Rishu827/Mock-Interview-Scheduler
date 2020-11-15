package com.example.MockProject.Controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockProject.Model.Interviewer;
import com.example.MockProject.Repository.InterviewerRepo;
import com.example.MockProject.ServiceImpl.SlotBookServiceImpl;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewerController {
	
	@Autowired
    private InterviewerRepo iRepo;
	@Autowired
	private SlotBookServiceImpl book;

	@PostMapping("/post/interviewer")
	@PreAuthorize("hasRole('USER')")
    ResponseEntity<Interviewer> createSlot(@Valid @RequestBody Interviewer interviewer) throws URISyntaxException {
		
		return book.createInterviewer(interviewer);
	}
	
	
	@GetMapping("/get/interviewer")
	@PreAuthorize("hasRole('USER')")
	List<?> findSlot(@Valid @RequestBody String email) throws URISyntaxException {
		
		return iRepo.findUserbyMail(email);
		
	}
}
