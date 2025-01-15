package com.dzapata.foro.security;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretGenerator {
    public static String generateSecret() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[32];
        secureRandom.nextBytes(secretBytes);
        String secret = Base64.getEncoder().encodeToString(secretBytes);

        try (FileWriter writer = new FileWriter("jwt-secret.txt")) {
            writer.write(secret);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return secret;
    }
}
