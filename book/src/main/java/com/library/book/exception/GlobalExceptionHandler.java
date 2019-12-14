package com.library.book.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

//	@ExceptionHandler({BookNotFoundException.class, AuthorNotFoundException.class})
//	public void springHandleNotFound(HttpServletResponse response) throws IOException{
//		response.sendError(HttpStatus.NOT_FOUND.value());
//	}
	
//	@ExceptionHandler({BookUnsupportedFieldPatchException.class})
//	public void springHandleUnsupportedFieldPatch(HttpServletResponse response) throws IOException{
//		response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
//	}
	
//	@ExceptionHandler({UserAlreadyExistsException.class})
//	public void springHandleValidation(HttpServletResponse response) throws IOException{
//		response.sendError(HttpStatus.CONFLICT.value());
//	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		
		List<String> errors = ex.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(x -> x.getDefaultMessage())
					.collect(Collectors.toList());
		
		body.put("errors", errors);
		
		return new ResponseEntity<>(body, headers, status);
	}
}
