package io.swagger.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class IndividualExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleALLException(Exception ex, WebRequest request) throws Exception {
	
		ResponseEntity<Object> responseEntity=null;
		if (ex instanceof NotFoundException) {
			responseEntity=new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		
		return responseEntity;
	}

	
	/*
	 * @Override protected ResponseEntity<Object> handleMethodArgumentNotValid(
	 * MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
	 * WebRequest request) {
	 * 
	 * 
	 * // EombExceptionResponseEntity responseEntity =new
	 * EombExceptionResponseEntity(ex.getMessage(),
	 * ex.getBindingResult().toString(), new Date(),request.getDescription(true));
	 * 
	 * return new
	 * ResponseEntity<Object>(responseEntity,HttpStatus.INTERNAL_SERVER_ERROR);
	 * 
	 * }
	 */
}
