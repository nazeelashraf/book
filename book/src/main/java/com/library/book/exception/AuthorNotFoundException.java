package com.library.book.exception;

public class AuthorNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6763278144976720317L;

	public AuthorNotFoundException(Long id) {
		super("Author not found for id: "+id);
	}
}
