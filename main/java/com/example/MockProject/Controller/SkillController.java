package com.example.MockProject.Controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockProject.ServiceImpl.SkillServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

	@Autowired
    private SkillServiceImpl skill;	
	
	@GetMapping("/get/skill")
   @PreAuthorize("hasRole('USER')")
	List<?> allSkill() throws URISyntaxException{
		return skill.getAllSkill();
	}

	@GetMapping("/get/skill/mail")
	@PreAuthorize("hasRole('USER')")
	List<?> specificSkill(@Valid @RequestBody String email) throws URISyntaxException{
		return skill.SkillByMail(email);
	}
}