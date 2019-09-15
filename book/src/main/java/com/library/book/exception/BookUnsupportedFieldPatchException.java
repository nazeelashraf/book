package com.library.book.exception;

import java.util.Set;

public class BookUnsupportedFieldPatchException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4221488356781844293L;

	public BookUnsupportedFieldPatchException(Set<String> keys) {
		super("Field "+keys.toString()+" update is not allowed");
	}
}
