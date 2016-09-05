package com.javaspringmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tshiamotaukobong on 2016/09/05.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    private String token;

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
