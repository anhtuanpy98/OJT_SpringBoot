package com.java.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//The @ControllerAdvice is an annotation, to handle the exceptions globally.
@ControllerAdvice
public class StudyExceptionController {

	@ExceptionHandler(value = StudyNotFoundException.class)
	public ResponseEntity<Object> exception(StudyNotFoundException exception) {
		return new ResponseEntity<>("Study not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NameStudentIsExisted.class)
	public ResponseEntity<Object> exception(NameStudentIsExisted exception) {
		return new ResponseEntity<>("Name is existed", HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}

