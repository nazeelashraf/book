package com.library.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.book.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
