package com.example.MockProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.Interview;

public interface InterviewRepo extends JpaRepository<Interview, Long>{
	
}
