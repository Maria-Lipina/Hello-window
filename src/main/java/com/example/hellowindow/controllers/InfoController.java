package com.example.hellowindow.controllers;

import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.FileHandler;
import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class InfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    private ScrollPane textPane;

    @FXML
    private Text aboutUsTextOut;

    @FXML
    void initialize() {
        signIn.setOnAction(actionEvent -> {
            signIn.getScene().getWindow().hide();
            new ViewLoader().loadView("logout_view.fxml", new Stage(), getClass());
        });
        signUp.setOnAction(actionEvent -> {
            signUp.getScene().getWindow().hide();
            new ViewLoader().loadView("signup_view.fxml", new Stage(), getClass());
        });

        aboutUsTextOut.setText(new FileHandler().getDescription());


    }

}
