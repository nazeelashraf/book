package com.library.book;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.library.book.model.Author;
import com.library.book.model.Book;
import com.library.book.repository.AuthorRepository;
import com.library.book.repository.BookRepository;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(
			AuthorRepository authorRepository, 
			BookRepository bookRepository
			) {
		
		return args -> {
			
			Author author = new Author("Dan", "Brown");
			
			Book book = new Book("Inferno", new BigDecimal("56.44"));
			book.setAuthor(author);
			author.getBooks().add(book);
			
			book = new Book("The Da Vinci Code", new BigDecimal("65.78"));
			book.setAuthor(author);
			author.getBooks().add(book);
			
			book = new Book("Origin", new BigDecimal("88.56"));
			book.setAuthor(author);
			author.getBooks().add(book);
			
			authorRepository.save(author);
			
			
			author = new Author("J. K.", "Rowling");
			
			book = new Book("Harry Potter and the Goblet of Fire", new BigDecimal("24.44"));
			book.setAuthor(author);
			author.getBooks().add(book);
			
			book = new Book("Harry Potter and the Chamber of Secrets", new BigDecimal("65.78"));
			book.setAuthor(author);
			author.getBooks().add(book);
			
			book = new Book("Harry Potter and the Half-blood Prince", new BigDecimal("76.56"));
			book.setAuthor(author);
			author.getBooks().add(book);
			
			authorRepository.save(author);
			
		};
	}

}
