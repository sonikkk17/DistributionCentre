package com.example.dc_clothes_warehouse.controller;


import com.example.dc_clothes_warehouse.model.dto.CreateUser;
import com.example.dc_clothes_warehouse.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    // @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    // public ResponseEntity<String> registerUserAccount(@RequestParam("username") String username, @RequestParam("password") String password){
    //     CreateUser user = new CreateUser(username, password);
    //     userRepository.save(user.toUser(passwordEncoder));
    //     return ResponseEntity.ok("User registered successfully.");
    // }

    @PostMapping("/register")
    public RedirectView registerUserAccount(@ModelAttribute CreateUser user) {
        userRepository.save(user.toUser(passwordEncoder));
        return new RedirectView("http://localhost:8080/login");
    }

    
}
