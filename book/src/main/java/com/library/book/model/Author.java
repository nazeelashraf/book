package com.library.book.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude= {"books"})
@EqualsAndHashCode(exclude="books")
public class Author {

	@Id @GeneratedValue
	private Long authorId;
	
	@NotEmpty(message="Please provide author first name")
	private String firstName;
	
	@NotEmpty(message="Please provide author last name")
	private String lastName;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL)
	@JsonIgnoreProperties("author")
	private Set<Book> books = new HashSet<>();
	
	public Author(String firstName, String lastName, Book...books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = Stream.of(books).collect(Collectors.toSet());
		this.books.forEach(x -> x.setAuthor(this));
	}
	
}
