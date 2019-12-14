package com.library.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.book.model.ApplicationUser;
import com.library.book.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
