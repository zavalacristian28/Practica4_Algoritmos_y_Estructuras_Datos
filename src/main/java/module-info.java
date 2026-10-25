module com.example.p4_aed {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.p4_aed to javafx.fxml;
    exports com.example.p4_aed;
}