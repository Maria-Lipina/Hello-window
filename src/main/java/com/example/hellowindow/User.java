package com.example.hellowindow;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {

    String name;
    String surname;
    String email;
    int passHash;

    public User (String email, int authHash) {
        this.email = email;
        this.passHash = authHash;
    }

    public User() {}

    public String passGen(int len){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
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
