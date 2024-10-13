package com.Book.Store.controller;

import com.Book.Store.model.Books;
import com.Book.Store.model.Cart;
import com.Book.Store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5500")
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/books")
    public ResponseEntity<Cart> addBookToCart(@RequestBody Books book) {

      return ResponseEntity.ok(cartService.addBookToCart(book));
    }

}
