module com.example.bank.bank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bank.bank to javafx.fxml;
    exports com.example.bank.bank;
}