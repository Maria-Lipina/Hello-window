package com.example.hellowindow.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.DatabaseHandler;
import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
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
    private Button logOut1;

    @FXML
    private Text totalCount;


    @FXML
    void initialize() {
        backHome.setOnAction(actionEvent -> {
            backHome.getScene().getWindow().hide();
            new ViewLoader().loadView("home_view.fxml", new Stage(), getClass());
        });
        logOut1.setOnAction(actionEvent -> {
            logOut1.getScene().getWindow().hide();
            new ViewLoader().loadView("logout_view.fxml", new Stage(), getClass());
            System.out.println("Logged out successfully");
        });
        deleteUser1.setOnAction(actionEvent -> {
            deleteUser1.getScene().getWindow().hide();
            new ViewLoader().loadView("logout_view.fxml", new Stage(), getClass());
            System.out.println("Profile successfully deleted");
        });

        totalCount.setText(String.format("%s %s", totalCount.getText(), new DatabaseHandler().getCount()));
    }

}
