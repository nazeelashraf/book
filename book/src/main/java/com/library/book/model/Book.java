package com.library.book.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@ToString(exclude= {"bookId"})
@EqualsAndHashCode(exclude="author")
public class Book {
	
	@Id @GeneratedValue
	private Long bookId;
	
	@NotEmpty(message="Please provide book name")
	private String name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="authorId", nullable=false)
	@JsonIgnoreProperties("books")
	private Author author;
	
	@NotNull(message="Please provide")
	@DecimalMin("1.00")
	private BigDecimal price;
	
	public Book(String name, BigDecimal price){
		this.name = name;
		this.price = price;
	}
}
