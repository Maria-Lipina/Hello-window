package com.example.hellowindow.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AllUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backHome;

    @FXML
    private Button deleteUser1;

    @FXML
    private Button logOut;

    @FXML
    void initialize() {
        assert backHome != null : "fx:id=\"backHome\" was not injected: check your FXML file 'allusers-view.fxml'.";
        assert deleteUser1 != null : "fx:id=\"deleteUser1\" was not injected: check your FXML file 'allusers-view.fxml'.";
        assert logOut != null : "fx:id=\"logOut\" was not injected: check your FXML file 'allusers-view.fxml'.";

    }
    
}
