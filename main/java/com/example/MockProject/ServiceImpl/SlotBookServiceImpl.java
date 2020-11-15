package com.example.MockProject.ServiceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MockProject.Exceptions.ApiRequestException;
import com.example.MockProject.Model.Interview;
import com.example.MockProject.Model.Interviewee;
import com.example.MockProject.Model.Interviewer;
import com.example.MockProject.Repository.InterviewRepo;
import com.example.MockProject.Repository.IntervieweeRepo;
import com.example.MockProject.Repository.InterviewerRepo;
import com.example.MockProject.Service.SlotBookService;

@Service
public class SlotBookServiceImpl implements SlotBookService{

	@Autowired
    private InterviewerRepo interviewerRepo;
	@Autowired
	private IntervieweeRepo intervieweeRepo; //target repository
	@Autowired
	private InterviewRepo interviewRepo; //match repository


    public ResponseEntity<Interviewer> createInterviewer(@Valid @RequestBody Interviewer interviewer) throws URISyntaxException {
    	
		interviewer.setStatus("unmatched");
		
		if (interviewerRepo.findInterviewer(interviewer.getPublicationDate(), interviewer.getEmail(), interviewer.getTag()) == 1 )
		{
			throw new ApiRequestException("Try a new slot. This slot already exist");
		}
			
			List<?> a = intervieweeRepo.matchInterviewee(interviewer.getEmail(), interviewer.getTag(), interviewer.getPublicationDate(), interviewer.getStatus());
			Iterator itr = a.iterator();
			if(itr.hasNext())
			{
				Object[] obj = (Object[]) itr.next();
				
				Interview match = new Interview();
				match.setDate(interviewer.getPublicationDate());
				match.setTag(interviewer.getTag());
				match.setInterviewerId(interviewer.getEmail());
				match.setIntervieweeId(String.valueOf(obj[1]));
				interviewRepo.save(match);
				
				try {
	        		String message ="Your role as an interviewee has been matched for an interview, with a suitable interviewer"+ "Kindly find your details below" +"\n"+"Interviewer id"+"\t"+match.getInterviewerId()+"\n"+"Interview Topic"+"\t"+match.getTag()+"\n"+"Date"+"\t"+match.getDate();
	        		String subject="HELLO INTERVIEWEE! MOCK INTERVIEW SCHEDULED";
					NotificationServiceImpl.sendEmail(match.getIntervieweeId(), message, subject);
					
					message ="Your role as an interviewer has been matched for an interview, with a suitable interviewer"+"\n"+ "Kindly find your details below" +"\n"+"Interviewee id"+"\t"+match.getIntervieweeId()+"\n"+"Interview Topic"+"\t"+match.getTag()+"\n"+"Date"+"\t"+match.getDate();
	        		subject="HELOO INTERVIEWER! MOCK INTERVIEW SCHEDULED";
					NotificationServiceImpl.sendEmail(match.getInterviewerId(), message, subject);
					try
					{
						interviewer.setStatus("matched");
						intervieweeRepo.updateInterviewee(match.getIntervieweeId(), match.getTag(), match.getDate(), "matched");
					}
					catch(Exception e)
					{
					}
					Interviewer result = interviewerRepo.save(interviewer);
					return ResponseEntity.created(new URI("/api/process/post/interviewer" + result.getId())).body(result);
				} 
	        	catch (MailException mailException) {
					System.out.println(mailException);
					throw new ApiRequestException("Kindly retry after some time.");
				}
			}
			
			Interviewer result = interviewerRepo.save(interviewer);
			return ResponseEntity.created(new URI("/api/process/post/interviewer" + result.getId())).body(result);
    }


    public ResponseEntity<Interviewee> createInterviewee(@Valid @RequestBody Interviewee interviewee) throws URISyntaxException {
    	
		interviewee.setStatus("unmatched");
		
		if (intervieweeRepo.findInterviewee(interviewee.getPublicationDate(), interviewee.getEmail(), interviewee.getTag()) == 1 )
		{
			throw new ApiRequestException("Try a new slot. This slot already exist");
		}
		
			List<?> a = interviewerRepo.matchInterviewer(interviewee.getEmail(), interviewee.getTag(), interviewee.getPublicationDate(), interviewee.getStatus());
			Iterator itr = a.iterator();
			if(itr.hasNext())
			{
				Object[] obj = (Object[]) itr.next();
				
				Interview match = new Interview();
				match.setDate(interviewee.getPublicationDate());
				match.setTag(interviewee.getTag());
				match.setInterviewerId(interviewee.getEmail());
				match.setIntervieweeId(String.valueOf(obj[1]));
				interviewRepo.save(match);
				
				try {
	        		String message ="Your role as an interviewee has been matched for an interview, with a suitable interviewer"+ "Kindly find your details below" +"\n"+"Interviewer id"+"\t"+match.getInterviewerId()+"\n"+"Interview Topic"+"\t"+match.getTag()+"\n"+"Date"+"\t"+match.getDate();
	        		String subject="MOCK INTERVIEW SCHEDULED";
					NotificationServiceImpl.sendEmail(match.getIntervieweeId(), message, subject);
					
					message ="Your role as an interviewer has been matched for an interview, with a suitable interviewer"+ "Kindly find your details below" +"\n"+"Interviewee id"+"\t"+match.getIntervieweeId()+"\n"+"Interview Topic"+"\t"+match.getTag()+"\n"+"Date"+"\t"+match.getDate();
	        		subject="MOCK INTERVIEW SCHEDULED";
					NotificationServiceImpl.sendEmail(match.getInterviewerId(), message, subject);
					try
					{
						interviewee.setStatus("matched");
						interviewerRepo.updateInterviewer(match.getIntervieweeId(), match.getTag(), match.getDate(), "matched");
					}
					catch(Exception e)
					{
					}
					Interviewee result = intervieweeRepo.save(interviewee);
					return ResponseEntity.created(new URI("/api/process/post/interviewee" + result.getId())).body(result);
				} 
	        	catch (MailException mailException) {
					System.out.println(mailException);
					throw new ApiRequestException("Kindly retry after some time");
				}
			}
			
			Interviewee result = intervieweeRepo.save(interviewee);
			return ResponseEntity.created(new URI("/api/process/post/interviewee" + result.getId())).body(result);
    }
}
