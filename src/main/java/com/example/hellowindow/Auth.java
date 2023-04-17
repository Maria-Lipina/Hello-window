package com.example.hellowindow;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Auth {

    private DatabaseHandler dbh;
    private User inSession;
    private LocalDateTime startSession;
    private static Auth SESSION;

    private Auth(){ }

    public static Auth getInstance() {
        if (SESSION == null) {
            synchronized (Auth.class) {
                if (SESSION == null) {
                    SESSION = new Auth();
                }
            }
        }
        return SESSION;
    }



    public boolean authCheck (String login, int authHash) {
        dbh = new DatabaseHandler();
        boolean result = dbh.getUser(login, authHash);
        if (result) this.setSession();
        return result;
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

    private void setSession() {
        inSession = dbh.getUserRecord();
        startSession = LocalDateTime.now();
    }

    public String getNameInSession(){
        return inSession.getFirstname();
    }
}
