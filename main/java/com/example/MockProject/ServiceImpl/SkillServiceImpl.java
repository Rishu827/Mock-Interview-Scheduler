package com.example.MockProject.ServiceImpl;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MockProject.Repository.SkillRepo;
import com.example.MockProject.Repository.UserRepo;
import com.example.MockProject.Service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
	
	@Autowired
    private SkillRepo sRepo;	
	@Autowired
	private UserRepo uRepo;
	
	@GetMapping("/get/skill")
	public List<?> getAllSkill() throws URISyntaxException{
		return sRepo.getSkill();
	}

	@GetMapping("/get/skill/mail")
	public List<?> SkillByMail(@Valid @RequestBody String email) throws URISyntaxException{
		List<?> tags = uRepo.findSkill(email);
		return tags;
	}

}
