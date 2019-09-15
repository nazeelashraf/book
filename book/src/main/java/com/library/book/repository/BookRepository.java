package com.library.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
