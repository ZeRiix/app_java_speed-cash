module com.example.apployaltyfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.datatransfer;


    opens com.example.apployaltyfx to javafx.fxml;
    exports com.example.apployaltyfx;
}