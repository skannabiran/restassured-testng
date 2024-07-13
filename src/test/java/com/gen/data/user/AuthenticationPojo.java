package com.gen.data.user;

import lombok.Data;

@Data
public class AuthenticationPojo {

    private String email;
    private String password;

    public AuthenticationPojo(String email, String password) {
        this.email = email;
        this.password = password;
    }

}