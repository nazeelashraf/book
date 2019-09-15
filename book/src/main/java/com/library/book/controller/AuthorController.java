package com.library.book.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.exception.AuthorNotFoundException;
import com.library.book.model.Author;
import com.library.book.model.Book;
import com.library.book.repository.AuthorRepository;
import com.library.book.repository.BookRepository;

@RestController
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/authors")
	List<Author> getAuthors(){
		return authorRepository.findAll();
	}
	
	@GetMapping("/authors/{id}")
	Author getAuthorById(@PathVariable Long id) {
		return authorRepository.findById(id)
				.orElseThrow(() -> new AuthorNotFoundException(id));
	}
	
	@PostMapping("/authors")
	Author saveAuthorAndBooks(@RequestBody Author author) {
		Set<Book> bookList = author.getBooks();
		
		author.setBooks(new HashSet<>());
		author.setAuthorId(null);
		
		bookList.stream().forEach(book -> {
				if(book.getBookId()!=null) {
					book = bookRepository.findById(book.getBookId())
							.orElse(book);
				}
				book.setAuthor(author);
				author.getBooks().add(bookRepository.save(book));
			}
		);
		
		return authorRepository.save(author);
	}
	
}
