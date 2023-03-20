package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField enterLogin;
    private String login;

    @FXML
    private PasswordField enterPassword;
    private String password;

    @FXML
    private Button seeAllUsers;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    void initialize() {
         signIn.setOnAction(actionEvent -> {
             System.out.println("Sign in button - menu for the authorized");
         });
        signUp.setOnAction(actionEvent -> {
            System.out.println("Sign up button - menu for new users");
        });
        seeAllUsers.setOnAction(actionEvent -> {
            System.out.println("See all users - list of the users saved in database");
        });

    }

}