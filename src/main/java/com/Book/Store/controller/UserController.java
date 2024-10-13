package com.Book.Store.controller;

import com.Book.Store.dto.RegisterDTO;
import com.Book.Store.dto.SignInDTO;
import com.Book.Store.execptions.EmailAlreadyExistsException;
import com.Book.Store.service.JwtService;
import com.Book.Store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5500")
public class UserController {
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private static final String SUCCESS_MESSAGE = " Successfully registered";

    // endpoint for customer sign up
    @PostMapping("/register/customer")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            userService.registerCustomer(registerDTO);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(SUCCESS_MESSAGE);
    }

    // endpoint for admin sign up
    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            userService.registerAdmin(registerDTO);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest().
                    body(e.getMessage());
        }
        return ResponseEntity.ok(SUCCESS_MESSAGE);
    }


    //endpoint for admin login
    @PostMapping("admin/login")
    public ResponseEntity<String> adminLogin(@Valid @RequestBody SignInDTO signInDTO) {
        String message = userService.loginAdmin(
                signInDTO.getEmail()
                , signInDTO.getPassword()
                , "ADMIN");

        if (message.equalsIgnoreCase("UNAUTHORIZED ACCESS")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("UNAUTHORIZED ACCESS");
        } else if (message.equalsIgnoreCase("Invalid email or password")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email or password");
        }
        return ResponseEntity.ok(message);

    }

    //endpoint for customer login
    @PostMapping("customer/login")
    public ResponseEntity<String> customerLogin(@Valid @RequestBody SignInDTO signInDTO) {
        // accept the return message from userService (JWT,UNAUTHORIZED ACCESS,INVALID EMAIL OR PASSWORD)
        String message = userService.loginAdmin(
                signInDTO.getEmail()
                , signInDTO.getPassword()
                , "CUSTOMER");


        if (message.equalsIgnoreCase("UNAUTHORIZED ACCESS")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("UNAUTHORIZED ACCESS");
        } else if (message.equalsIgnoreCase("Invalid email or password")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("INVALID EMAIL OR PASSWORD");
        }
        //returns JWT
        return ResponseEntity.ok(message);

    }
}




