package com.Book.Store.controller;

import com.Book.Store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5500")
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

//    @PostMapping("/books")
//    public ResponseEntity<Cart> addBookToCart(@RequestBody Books book) {
//
//      return ResponseEntity.ok(cartService.addBookToCart(book));
//    }

}
