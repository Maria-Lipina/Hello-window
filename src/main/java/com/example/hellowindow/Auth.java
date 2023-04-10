package com.example.hellowindow;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Auth {

    public boolean authCheck (String login, int authHash) {
        return new DatabaseHandler().getUser(login, authHash);
    }

    public String passGen(int len){
        final String chars =
                "0123456789ABCDEFGHIJKLMN01234OPQRSTUVWXYZ0123456789abcdefghijklmn56789opqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        return IntStream.range(0, len)
                .mapToObj(randomIndex -> String.valueOf(
                        chars.charAt(random.nextInt(chars.length()))))
                .collect(Collectors.joining());
    }

}
