module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.CS_IA_2 to javafx.fxml;
    exports com.example.CS_IA_2;
}