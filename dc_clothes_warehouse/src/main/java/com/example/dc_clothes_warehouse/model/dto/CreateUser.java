package com.example.dc_clothes_warehouse.model.dto;

import  com.example.dc_clothes_warehouse.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
public class CreateUser {

    private String username;
    private String password;


    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .build();
    }


    public CreateUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
