package com.example.dc_clothes_warehouse.config;


import com.example.dc_clothes_warehouse.model.User;
import com.example.dc_clothes_warehouse.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
        .requestMatchers(toH2Console()).permitAll()
        // .requestMatchers("/api/DistributionCentres")
        // .hasRole("USER")
        .anyRequest().permitAll().and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("http://localhost:8080/dc")
        .and().logout().logoutSuccessUrl("/");
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
