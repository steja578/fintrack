package com.fintrack.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        // Change this to the password you want to encode
        String rawPassword = "pass123";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("BCrypt hash for \"" + rawPassword + "\":");
        System.out.println(encodedPassword);
    }
}
