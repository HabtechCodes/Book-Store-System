package com.Book.Store.service;

import com.Book.Store.dto.RegisterDTO;
import com.Book.Store.execptions.EmailAlreadyExistsException;
import com.Book.Store.model.Role;
import com.Book.Store.model.User;
import com.Book.Store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void registerCustomer(RegisterDTO registerDTO) throws EmailAlreadyExistsException{

        if(this.emailExists(registerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder
                        .encode(registerDTO
                        .getPassword()));
        user.setRole(Role.CUSTOMER);

        userRepository.save(user);

    }
    public void registerAdmin(RegisterDTO registerDTO) throws EmailAlreadyExistsException {

        if(emailExists(registerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder
                .encode(registerDTO
                        .getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }
    public String loginAdmin(String email, String password, String role) {


        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password));

            UserDetails user = (UserDetails) authentication.getPrincipal();
            if(!user.getAuthorities().contains(new SimpleGrantedAuthority(role))) {
                boolean isAuthenticated = !(authentication.isAuthenticated());
                authentication.setAuthenticated(isAuthenticated);
                return "UNAUTHORIZED ACCESS";
            }
            return jwtService.generateToken(email);

        }catch (AuthenticationException e){
           return ("Invalid email or password");
        }
    }


}
