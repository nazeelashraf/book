package com.library.book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	public Role(String name) {
		this.name = name;
	}
}
