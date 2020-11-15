package com.example.MockProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long>{

	Skill findByTag(String tag);
	
	@Query( 
            value = "SELECT skill.tag FROM skill",
            nativeQuery = true
    )
	List<?> getSkill();
}
