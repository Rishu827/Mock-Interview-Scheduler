package com.example.MockProject.Service;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

public interface SkillService {
	
	public List<?> getAllSkill() throws URISyntaxException;
	
	public List<?> SkillByMail(@Valid @RequestBody String email) throws URISyntaxException;

}
