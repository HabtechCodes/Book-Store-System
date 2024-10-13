package com.Book.Store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "First name can't be blank")
    private String firstName;
    @NotBlank(message = "First name can't be blank")
    private String lastName;
    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "First name can't be blank")
    private String password;
}
