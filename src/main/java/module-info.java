module com.example.hellowindow {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hellowindow to javafx.fxml;
    exports com.example.hellowindow;
    exports com.example.hellowindow.controllers;
    opens com.example.hellowindow.controllers to javafx.fxml;
}