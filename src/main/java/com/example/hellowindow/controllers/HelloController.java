package com.example.hellowindow.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.HelloApplication;
import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            signIn.getScene().getWindow().hide();
            new ViewLoader().loadView("home-view.fxml", new Stage(), getClass());
        });
        signUp.setOnAction(actionEvent -> {
            signUp.getScene().getWindow().hide();
            new ViewLoader().loadView("signup-form-view.fxml", new Stage(), getClass());

        });
        seeAllUsers.setOnAction(actionEvent -> {
            seeAllUsers.getScene().getWindow().hide();
            new ViewLoader().loadView("allusers-view.fxml", new Stage(), getClass());
        });

    }

}