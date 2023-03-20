module com.example.hellowindow {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hellowindow to javafx.fxml;
    exports com.example.hellowindow;
}