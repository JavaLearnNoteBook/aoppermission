package com.molly.aoppermission.aoppermission.Model;

import lombok.Data;

@Data
public class User {
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
