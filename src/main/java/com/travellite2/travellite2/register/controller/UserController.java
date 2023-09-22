
package com.travellite2.travellite2.register.controller;

import com.travellite2.travellite2.register.entity.User;
import com.travellite2.travellite2.register.model.UserJson;
import com.travellite2.travellite2.register.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


import java.util.Date;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String> loginUser(@RequestBody UserJson userJson) throws NoSuchAlgorithmException {

        User loginUser = userService.getUser(userJson);

        if (loginUser != null) {
            // checks that the username exists

            // Create the JWT token
            String token = generateJWTToken(loginUser.getUserName());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserJson user) {
        User registeredUser = userService.registerUser(user);
        //creates a new user
        return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
    }

    private String generateJWTToken(String userName) throws NoSuchAlgorithmException {
        // Set the expiration time for the token (e.g., 1 hour from now)
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        // Create the claims for the JWT token
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("role", "user"); // You can add additional custom claims as needed

        // Generate the secret key (you should keep this key safe)
        SecretKey secretKey = JwtUtil.generateSecretKey();

        // Generate the token using the secret key

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(secretKey) // Use the secret key to sign the token
                .compact();
    }

}
