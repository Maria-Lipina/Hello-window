package com.example.hellowindow.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AllUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backHome;

    @FXML
    private Button deleteUser1; //Удалить свой профиль в базовой версии. Или чей-то ещё, если у пользователя права администратора

    @FXML
    private Button logOut;

    @FXML
    void initialize() {
        backHome.setOnAction(actionEvent -> {
            backHome.getScene().getWindow().hide();
            new ViewLoader().loadView("home-view.fxml", new Stage(), getClass());
        });
        logOut.setOnAction(actionEvent -> {
            logOut.getScene().getWindow().hide();
            new ViewLoader().loadView("hello-view.fxml", new Stage(), getClass());
        });

    }

}
