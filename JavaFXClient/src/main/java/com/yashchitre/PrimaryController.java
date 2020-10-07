package com.yashchitre;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PrimaryController {

    private boolean mainView = true;

    @FXML private BorderPane borderPane;

    @FXML private Button mainViewButton;
    @FXML private Button savedViewButton;

    @FXML private HBox searchHBox;
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private Button checkBoxActionButton;

    @FXML private TableView<QueryItem> result;
    @FXML private TableColumn<QueryItem, CheckBox> select;
    @FXML private TableColumn<QueryItem, Text> content;

    @FXML
    private void searchButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    private void checkBoxActionButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    private void resultItemClick(MouseEvent mouseEvent) {
    }

    @FXML
    private void mainViewButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    private void savedViewButtonClick(ActionEvent actionEvent) {
    }
}
