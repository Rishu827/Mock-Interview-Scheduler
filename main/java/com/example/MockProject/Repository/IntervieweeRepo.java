package com.example.MockProject.Repository;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MockProject.Model.Interviewee;

public interface IntervieweeRepo extends JpaRepository<Interviewee, Long>{

	@Query(
            value = "SELECT COUNT(*) FROM interviewee WHERE interviewee.publication_date = :date and interviewee.email = :mail and interviewee.tag = :tag",
            nativeQuery = true
    )
    int findInterviewee(Date date, String mail, String tag);
	
	@Query(
            value = "SELECT * FROM interviewee WHERE interviewee.email = :email and interviewee.publication_date >= CURRENT_DATE",
            nativeQuery = true
    )
	List<?> findUserbyMail(String email);
	
	@Query(
            value = "DELETE from interviewee where interviewee.publication_date < CURRENT_DATE and interviewee.email = :email",
            nativeQuery = true	
    )
	void deleteOldInterviewee(String email);
	
	@Query(
            value = "SELECT * from interviewee where interviewee.publication_date = :date and interviewee.email != :email and interviewee.tag = :tag and interviewee.status = :status ",
            nativeQuery = true	
    )
	List<?> matchInterviewee(String email, String tag, Date date, String status);
	
	@Query(
            value = "UPDATE interviewee set status = :status where interviewee.publication_date = :date and interviewee.email = :email and interviewee.tag = :tag",
            nativeQuery = true	
    )
	void updateInterviewee(String email, String tag, Date date, String status);

}
