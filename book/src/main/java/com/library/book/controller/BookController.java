package com.library.book.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.exception.BookNotFoundException;
import com.library.book.exception.BookUnsupportedFieldPatchException;
import com.library.book.model.Author;
import com.library.book.model.Book;
import com.library.book.repository.AuthorRepository;
import com.library.book.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/books")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	List<Book> findAll(){
		return bookRepository.findAll(); 
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/books")
	@Secured({"ROLE_ADMIN"})
	Book newBook(@Valid @RequestBody Book newBook) {
		if(newBook.getAuthor().getAuthorId()!=null) {
			Author author = authorRepository.findById(newBook.getAuthor().getAuthorId())
					.orElse(newBook.getAuthor());
			newBook.setAuthor(author);
		}
		
		return bookRepository.save(newBook);
	}
	
	@GetMapping("/books/{id}")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	Book findOne(@PathVariable Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
	}
	
	@PutMapping("/books/{id}")
	@Secured({"ROLE_ADMIN"})
	Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {
		if(newBook.getAuthor().getAuthorId()!=null) {
			Author author = authorRepository.findById(newBook.getAuthor().getAuthorId())
					.orElse(newBook.getAuthor());
			newBook.setAuthor(author);
		}
		
		return bookRepository.findById(id)
				.map(x -> {
					x.setName(newBook.getName());
					x.setAuthor(newBook.getAuthor());
					x.setPrice(newBook.getPrice());
					
					return bookRepository.save(x);
				})
				.orElseThrow(() -> {
					throw new BookNotFoundException(id);
				});
	}
	
	@PatchMapping("/books/{id}")
	@Secured({"ROLE_ADMIN"})
	Book patch(@RequestBody Map<String, Author> update, @PathVariable Long id) {
		return bookRepository.findById(id)
				.map(x -> {
					Author author = update.get("author");
					
					if(!StringUtils.isEmpty(author)) {
						x.setAuthor(author);
						return bookRepository.save(x);
					} else {
						throw new BookUnsupportedFieldPatchException(update.keySet());
					}
				})
				.orElseGet(() -> {
					throw new BookNotFoundException(id);
				});
	}
	
	
	@DeleteMapping("/books/{id}")
	@Secured({"ROLE_ADMIN"})
	void deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
	}
	
	@GetMapping("/search/books/{search}")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	Set<Book> searchAll(@PathVariable String search) {
		Set<Book> results = new HashSet<>();
		String[] queries = search.toLowerCase().split(";");
		
		for(String query: queries) {
			bookRepository.findAll().stream().forEach(
					book -> {
						if(book.toString().toLowerCase().contains(query))
							results.add(book);
						}
					);
		}
		
		return results;
	}
}
