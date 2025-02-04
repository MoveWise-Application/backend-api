package com.movewise.movewise_api.configuration;

import io.github.cdimascio.dotenv.Dotenv;

public class JwtConfiguration {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getJwtSecret() {
        return dotenv.get("JWT_SECRET");
    }

    public static long getJwtExpirationTime() {
        return Long.parseLong(dotenv.get("JWT_EXPIRATION_TIME"));
    }
}
