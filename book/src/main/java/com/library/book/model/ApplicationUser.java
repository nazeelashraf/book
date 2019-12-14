package com.library.book.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApplicationUser {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String username;
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="user_roles",
			joinColumns=@JoinColumn(
					name="user_id", referencedColumnName="id"
					),
			inverseJoinColumns=@JoinColumn(
					name="role_id", referencedColumnName="id"
					)
			)
	@JsonIgnoreProperties("users")
	private Collection<Role> roles;
	
}
