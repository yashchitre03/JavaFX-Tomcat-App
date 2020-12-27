module JavaFXClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.google.gson;
    requires javafx.web;

    opens com.yashchitre to javafx.fxml;
    exports com.yashchitre;
}