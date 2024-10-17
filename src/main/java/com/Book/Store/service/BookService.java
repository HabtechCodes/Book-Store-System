package com.Book.Store.service;

import com.Book.Store.model.Books;
import com.Book.Store.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BookService {

    private final BooksRepository booksRepository;


    //view all Books
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public void addBook(Books books) {
        booksRepository.save(books);
    }
    public Books getBookById(int bookId) {
        return booksRepository
                .findById(bookId)
                .orElseThrow(
                        ()-> new RuntimeException("Book with id: " + bookId + "Not Found"));
    }

    public void deleteBook(int bookId){
        booksRepository.deleteById(bookId);
    }


    public List<Books> searchBooksByTitle(String title) {
        return booksRepository.findByBookTittleContaining(title);
    }
}
