package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
    private Button passGenerate;

    @FXML
    private TextField password;

    @FXML
    private Button saveProfile;

    @FXML
    private TextField surname;

    @FXML
    void initialize() {
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'signup-form-view.fxml'.";
        assert keepSigned != null : "fx:id=\"keepSigned\" was not injected: check your FXML file 'signup-form-view.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'signup-form-view.fxml'.";
        assert passGenerate != null : "fx:id=\"passGenerate\" was not injected: check your FXML file 'signup-form-view.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'signup-form-view.fxml'.";
        assert saveProfile != null : "fx:id=\"saveProfile\" was not injected: check your FXML file 'signup-form-view.fxml'.";
        assert surname != null : "fx:id=\"surname\" was not injected: check your FXML file 'signup-form-view.fxml'.";

    }

}
