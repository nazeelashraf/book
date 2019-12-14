package com.library.book;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.library.book.model.ApplicationUser;
import com.library.book.model.Author;
import com.library.book.model.Book;
import com.library.book.model.Role;
import com.library.book.repository.AuthorRepository;
import com.library.book.repository.BookRepository;
import com.library.book.repository.RoleRepository;
import com.library.book.repository.UserRepository;

@SpringBootApplication
public class BookApplication {

	@Bean
	public SCryptPasswordEncoder sCryptPasswordEncoder() {
		return new SCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(
			AuthorRepository authorRepository, 
			BookRepository bookRepository,
			UserRepository userRepository,
			RoleRepository roleRepository
			) {
		
		return args -> {
			
			Role adminRole = new Role("ROLE_ADMIN");
			Role userRole = new Role("ROLE_USER");
			
			ApplicationUser user = new ApplicationUser();
			user.setPassword(sCryptPasswordEncoder().encode("admin"));
			user.setUsername("admin");
			user.setRoles(Arrays.asList(adminRole, userRole));
			userRepository.save(user);
			
			roleRepository.save(userRole);
			
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
