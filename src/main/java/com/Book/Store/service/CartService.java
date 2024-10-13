package com.Book.Store.service;

import com.Book.Store.model.Books;
import com.Book.Store.model.Cart;
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


    public Cart addBookToCart(Books book) {

        Cart cart = book.getCart();

        // get book list and add to cart
        cart.getBooksList().add(book);
        book.setCart(cart);

      return cartRepository.save(cart);
    }

}
