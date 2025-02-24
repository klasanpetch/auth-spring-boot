package com.klasanpetch.auth_spring_boot.controllers;

import com.klasanpetch.auth_spring_boot.entites.Users;
import com.klasanpetch.auth_spring_boot.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    UserService service;

    @Value("${COOKIE_EXP}")
    private int cookieEXP;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user, HttpServletResponse response) {
        String accessToken = service.verify(user);

        if (accessToken == null || accessToken.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(cookieEXP)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok("Login successful!");
    }
}
