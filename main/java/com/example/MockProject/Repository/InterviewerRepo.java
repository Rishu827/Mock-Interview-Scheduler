package com.example.MockProject.Repository;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.Interviewer;


public interface InterviewerRepo extends JpaRepository<Interviewer, Long>{

	@Query(
            value = "SELECT COUNT(*) FROM interviewer WHERE interviewer.publication_date = :date and interviewer.email = :mail and interviewer.tag = :tag",
            nativeQuery = true
    )
    int findInterviewer(Date date, String mail, String tag);
	
	@Query(
            value = "SELECT * FROM interviewer WHERE interviewer.email = :email and interviewer.publication_date >= CURRENT_DATE",
            nativeQuery = true
    )
	List<?> findUserbyMail(String email);
	
	@Query(
            value = "DELETE from interviewer where interviewer.publication_date < CURRENT_DATE and interviewer.email = :email",
            nativeQuery = true	
    )
	void deleteOldInterviewer(String email);
	
	@Query(
            value = "SELECT * from interviewer where interviewer.publication_date = :date and interviewer.email != :email and interviewer.tag = :tag and interviewer.status = :status",
            nativeQuery = true	
    )
	List<?> matchInterviewer(String email, String tag, Date date, String status);
	
	@Query(
            value = "UPDATE interviewer set status = :status where interviewer.publication_date = :date and interviewer.email = :email and interviewer.tag = :tag",
            nativeQuery = true	
    )
	void updateInterviewer(String email, String tag, Date date, String status);


}
