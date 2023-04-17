package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.Auth;
import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteUser;

    @FXML
    private Button logOut;

    @FXML
    private Button seeAllUsers;

    @FXML
    private Text welcome;


    @FXML
    void initialize() {
        logOut.setOnAction(actionEvent -> {
            logOut.getScene().getWindow().hide();
            new ViewLoader().loadView("logout-view.fxml", new Stage(), getClass());
            System.out.println("Logged out successfully");
        });
        seeAllUsers.setOnAction(actionEvent -> {
            seeAllUsers.getScene().getWindow().hide();
            new ViewLoader().loadView("allusers-view.fxml", new Stage(), getClass());
        });
        deleteUser.setOnAction(actionEvent -> {
            seeAllUsers.getScene().getWindow().hide();
            new ViewLoader().loadView("logout-view.fxml", new Stage(), getClass());
            System.out.println("Profile successfully deleted");
        });

        welcome.setText(String.format("Welcome, %s", Auth.getInstance().getNameInSession()));

    }


}
