package com.example.hellowindow;

public class ConfigConst {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "1why3not5like7Linux9";
    protected String dbName = "jfx";

    //Зачем два отдельных класса, а тут ещё и публичная статика?
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "id";
    public static final String USER_FIRSTNAME = "firstname";
    public static final String USER_LASTNAME = "lastname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASS_HASH = "password_hash";

}
