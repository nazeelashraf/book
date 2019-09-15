package com.library.book.exception;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 484605695024695611L;

	public BookNotFoundException(Long id) {
		super("Book id not found: "+id);
	}
	
}
