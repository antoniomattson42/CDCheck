module com.example.cdcheck {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cdcheck to javafx.fxml;
    exports com.example.cdcheck;
}