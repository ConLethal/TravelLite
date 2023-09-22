package com.travellite2.travellite2.register.controller;

import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

public class JwtUtil {
    public static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        return keyGenerator.generateKey();
    }
}