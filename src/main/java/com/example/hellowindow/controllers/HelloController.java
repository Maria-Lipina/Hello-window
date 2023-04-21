package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.Auth;
import com.example.hellowindow.ViewLoader;
import com.example.hellowindow.animation.Shake;
import javafx.fxml.FXML;
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

    @FXML
    private PasswordField enterPassword;

    @FXML
    private Button infoPage;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;


    @FXML
    void initialize() {

        signIn.setOnAction(actionEvent -> {

            String login = enterLogin.getText().trim();
            int authHash = enterPassword.getText().trim().hashCode();

            if (!login.equals("") && authHash != 0 && Auth.getInstance().authCheck(login, authHash)) {
                signIn.getScene().getWindow().hide();
                new ViewLoader().loadView("home_view.fxml", new Stage(), getClass());

            } else {
                new Shake(enterLogin).playAnim();
                new Shake(enterPassword).playAnim();
            }
        });
        signUp.setOnAction(actionEvent -> {
            signUp.getScene().getWindow().hide();
            new ViewLoader().loadView("signup_view.fxml", new Stage(), getClass());
        });
        infoPage.setOnAction(actionEvent -> {
            infoPage.getScene().getWindow().hide();
            new ViewLoader().loadView("info_view.fxml", new Stage(), getClass());
        });

    }

}