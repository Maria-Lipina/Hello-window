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
        this.r = Role.USER;
    }

    public User() {}

    public String passGen(int len){
        final String chars =
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        return IntStream.range(0, len)
                .mapToObj(randomIndex -> String.valueOf(
                        chars.charAt(random.nextInt(chars.length()))))
                .collect(Collectors.joining());
    }

    public boolean authCheck (String login, int authHash) {
        new DatabaseHandler().connect();
        return true;
    }

    public static int getCount () {
        return 0;
    }

}
