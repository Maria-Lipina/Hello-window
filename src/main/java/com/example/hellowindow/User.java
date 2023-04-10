package com.example.hellowindow;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {
    enum Role {
        USER
    }

    String name;
    String surname;
    String email;
    int passHash;
    Role r;



    public User (String email, int authHash) {
        this.email = email;
        this.passHash = authHash;
    }

    public User() {}

    public static int getCount () {
        return 0;
    }


}
