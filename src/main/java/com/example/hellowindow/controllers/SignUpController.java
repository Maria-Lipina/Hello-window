package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.Auth;
import com.example.hellowindow.DatabaseHandler;
import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email;

    @FXML
    private CheckBox keepSigned;

    @FXML
    private TextField name;

    @FXML
    private CheckBox generatePassword;

    @FXML
    private TextField password;

    @FXML
    private Button saveProfile;

    @FXML
    private TextField surname;


    @FXML
    void initialize() {
        saveProfile.setOnAction(actionEvent -> {

            if (generatePassword != null && generatePassword.isSelected()) {
                String pass = Auth.getInstance().passGen(8);
                System.out.println(pass);
                new DatabaseHandler().addUser(
                        name.getText(), surname.getText(), email.getText(), pass.hashCode());
            } else {
                new DatabaseHandler().addUser(
                        name.getText(), surname.getText(), email.getText(), password.getText().hashCode());
            }
            saveProfile.getScene().getWindow().hide();
            new ViewLoader().loadView("home-view.fxml", new Stage(), getClass());
        });
    }


}
