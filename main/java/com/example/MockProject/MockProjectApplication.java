package com.example.MockProject;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.MockProject.Model.Skill;
import com.example.MockProject.Model.User;
import com.example.MockProject.Repository.SkillRepo;
import com.example.MockProject.Repository.UserRepo;


@SpringBootApplication
public class MockProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
	}
		
		@Bean
		public CommandLineRunner init( UserRepo userRepo, SkillRepo sRepo){
			return args -> {

			    createUser(userRepo, "Rishabh", "Singhal", "r.singhal.com@gmail.com","C,C++,Java", "M", "8273091918", "rishabh");
			    String[] tag = {"C","C++","Python","Java"};
			   createSkill(tag, sRepo);
			};
		}


		private void createSkill(String[] tag, SkillRepo sRepo) {
			for(String i : tag)
			{
				Skill skill = new Skill();
				skill.setTag(i);
				sRepo.save(skill);
			}
		}

		private User createUser(UserRepo userRepo, String f_name, String l_name, String email, String tags, String gender, String mobile, String password) {
	        User user = new User();

	        user.setFirst_name(f_name);
	        user.setLast_name(l_name);
	        user.setEmail(email);
	        user.setTags(tags);
	        user.setGender(gender);
	        user.setMobile(mobile);
	        user.setPassword(password);
	        return userRepo.save(user);

	}

}
