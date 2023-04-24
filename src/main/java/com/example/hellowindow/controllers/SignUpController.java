package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.Auth;
import com.example.hellowindow.DatabaseHandler;
import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
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
    private PasswordField password;

    @FXML
    private Text clipboardSignal;

    @FXML
    private Button saveProfile;

    @FXML
    private TextField surname;


    @FXML
    void initialize() {

        generatePassword.setOnAction(actionEvent -> {
            if (generatePassword.isSelected()) {
                String pass = Auth.getInstance().passGen(8);
                System.out.println(pass);
                password.setText(pass);

                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(password.getText());
                clipboard.setContent(content);
                System.out.println("Буфер обмена " + content.getString());
                clipboardSignal.setVisible(true);
            }
            else {
                password.setText("");
                clipboardSignal.setVisible(false);
            }
        });



        saveProfile.setOnAction(actionEvent -> {
            System.out.println(" должно при сохранении пароля:" + password.getText());
            new DatabaseHandler().addUser(
                    name.getText(), surname.getText(), email.getText(), password.getText().hashCode());
            saveProfile.getScene().getWindow().hide();
            if (keepSigned.isSelected()) {
                new ViewLoader().loadView("home_view.fxml", new Stage(), getClass());
            } else {
                new ViewLoader().loadView("logout_view.fxml", new Stage(), getClass());
            }
        });
    }


}
