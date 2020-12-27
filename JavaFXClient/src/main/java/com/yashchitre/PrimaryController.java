package com.yashchitre;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import com.google.gson.Gson;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        content.prefWidthProperty().bind(result.widthProperty().subtract(select.widthProperty()));

        select.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
    }

    @FXML
    protected void mainViewButtonClick(ActionEvent actionEvent) {
        mainView = true;
        searchField.setVisible(true);
        searchButton.setVisible(true);
        checkBoxActionButton.setText("Save Selected");
        result.getItems().clear();
    }

    @FXML
    protected void savedViewButtonClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        mainView = false;
        searchField.setVisible(false);
        searchButton.setVisible(false);
        checkBoxActionButton.setText("Remove Selected");
        result.getItems().clear();





        URL url = new URL("http://localhost:8080/saved");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Scanner sc = new Scanner(url.openStream());
        String response = sc.nextLine();
        sc.close();
        Gson gson = new Gson();
        QueryItem[] temp = gson.fromJson(response, QueryItem[].class);
        for(QueryItem qt: temp) {
            System.out.println(qt);
        }

        QueryItem temp2 = gson.fromJson(response, QueryItem.class);
        System.out.println(temp2.getPageId() +
                temp2.getContent() + temp2.getText());




    }

    @FXML
    protected void searchButtonClick(ActionEvent actionEvent) {
        String query = searchField.getText();
        if(query.isEmpty()) return;

        System.out.println(query);
        ObservableList<QueryItem> items = Json.fetch(query);
        result.getItems().clear();

        items.forEach(queryItem -> queryItem.getText().wrappingWidthProperty().bind(content.widthProperty()));

        result.getItems().addAll(items);
    }

    @FXML
    protected void checkBoxActionButtonClick(ActionEvent actionEvent) {
        if(mainView) {
            System.out.println("Items Saved:\n");
            result.getItems().forEach(queryItem -> {
                if (queryItem.getCheckBox().isSelected()) {
                    System.out.println(queryItem.getContent());
                }
            });
        }
        else {
            System.out.println("Items Deleted:\n");
            result.getItems().forEach(queryItem -> {
                if (queryItem.getCheckBox().isSelected()) {
                    System.out.println(queryItem.getContent());
                }
            });
        }
    }

    @FXML
    protected void resultItemClick(MouseEvent mouseEvent) {

        if(mouseEvent.getClickCount() == 2) {
            try {
                long pageId = result.getSelectionModel().getSelectedItem().getPageId();
                String url = Json.getUrlByPageId(pageId);

                WebView webView = new WebView();
                WebEngine webEngine = webView.getEngine();
                webEngine.load(url);

                Stage stage = new Stage();
                stage.setTitle("JavaFX WebView Example");
                Scene scene = new Scene(webView);

                stage.setScene(scene);
                stage.showAndWait();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
