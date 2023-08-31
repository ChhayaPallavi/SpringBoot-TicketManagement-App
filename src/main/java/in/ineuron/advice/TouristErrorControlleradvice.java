package in.ineuron.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import in.ineuron.error.ErrorDetails;
import in.ineuron.exception.TouristNotFoundException;

@RestControllerAdvice
public class TouristErrorControlleradvice {
	
	@ExceptionHandler(TouristNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTouristNotFound(TouristNotFoundException tf){
		System.out.println("TouristErrorControlleradvice.handleTouristNotFound()");
		
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), tf.getMessage(), "404-NotFound");
		
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails>handleAllProblems(Exception e){
		System.out.println("TouristErrorControlleradvice.handleAllProblems()");
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Problem in execution");
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	

}
