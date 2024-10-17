package com.Book.Store.repository;

import com.Book.Store.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {


    List<Books> findByBookTittleContaining(String title);
}