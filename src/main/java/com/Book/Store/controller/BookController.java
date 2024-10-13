package com.Book.Store.controller;

import com.Book.Store.model.Books;
import com.Book.Store.model.Cart;
import com.Book.Store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5500")
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Books book) {
        bookService.addBook(book);
        return ResponseEntity.ok()
                .body("Book added successfully!");
    }

    public ResponseEntity<String> deleteBookById(int bookId){
        bookService.deleteBook(bookId);

        return ResponseEntity.ok()
                .body("Book deleted successfully!");
    }

    //for testing only
    @GetMapping("/load")
    public String loadBooksToDb() {
        List<Books> bookList = Arrays.asList(
                new Books(1,"Author1","Java programming",2000,new Cart()),
               new Books (2,"Author2","SpringBoot",2000,new Cart()));

        return "book Loaded Successfully";
    }


}
