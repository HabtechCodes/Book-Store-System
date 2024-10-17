package com.Book.Store.controller;

import com.Book.Store.model.Books;
import com.Book.Store.model.Cart;
import com.Book.Store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{bookId}")
    public ResponseEntity<Books> getBookById(@PathVariable int bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Books>> searchBooksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
    }
    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Books book) {
        bookService.addBook(book);
        return ResponseEntity.ok()
                .body("Book added successfully!");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable int bookId){
        try {
            bookService.deleteBookById(bookId);
            return ResponseEntity.ok()
                    .body("Book deleted successfully!");
        }catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       


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