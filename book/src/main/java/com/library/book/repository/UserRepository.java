package com.library.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.book.model.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long>{
	ApplicationUser findByUsername(String username);
}
