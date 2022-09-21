package com.practice.projectitproger.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.practice.projectitproger.DB;
import com.practice.projectitproger.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea add_anons;

    @FXML
    private TextArea add_full_text;

    @FXML
    private TextField add_new_title_article;

    @FXML
    void Add_article(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String title = add_new_title_article.getCharacters().toString();
        String anons = add_anons.getText();
        String full_text = add_full_text.getText();

        add_new_title_article.setStyle("-fx-border-color: #fafafa");
        add_anons.setStyle("-fx-border-color: #fafafa");
        add_full_text.setStyle("-fx-border-color: #fafafa");

        if (title.length() <= 5)
            add_new_title_article.setStyle("-fx-border-color: #e06249");
        else if (anons.length() <= 15)
            add_anons.setStyle("-fx-border-color: #e06249");
        else if (full_text.length() <= 25)
            add_full_text.setStyle("-fx-border-color: #e06249");
        else {
            DB db = new DB();
            db.addArticle(title, anons, full_text);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HelloApplication.setScene("articles-panel.fxml", stage);

    }

    @FXML
    void initialize() {


    }

}
