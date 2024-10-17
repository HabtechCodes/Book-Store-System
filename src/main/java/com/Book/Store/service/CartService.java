package com.Book.Store.service;

import com.Book.Store.repository.BooksRepository;
import com.Book.Store.repository.CartRepository;
import com.Book.Store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;
    private final BookService bookService;


//    public Cart addBookToCart(int bookId) {
//
//        Books book = booksRepository.findById(bookId)
//                .orElseThrow(()-> new RuntimeException("Book not found"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(()-> new RuntimeException("User not found "));
//        Books newBook = new Books()
//
//        Cart cart =  new Cart(book,user);
//    }

}
