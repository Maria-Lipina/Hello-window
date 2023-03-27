package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    void initialize() {
        assert deleteUser != null : "fx:id=\"deleteUser\" was not injected: check your FXML file 'home-view.fxml'.";
        assert logOut != null : "fx:id=\"logOut\" was not injected: check your FXML file 'home-view.fxml'.";
        assert seeAllUsers != null : "fx:id=\"seeAllUsers\" was not injected: check your FXML file 'home-view.fxml'.";

    }


}
