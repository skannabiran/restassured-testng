package com.gen.data.booking;

public class TokenBuilder {

    public static Tokencreds getToken() {
        return Tokencreds.builder()
                .username("admin")
                .password("password123")
                .build();
    }

}