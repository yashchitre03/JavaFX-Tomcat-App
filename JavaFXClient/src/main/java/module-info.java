module JavaFXClient {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.yashchitre to javafx.fxml;
    exports com.yashchitre;
}