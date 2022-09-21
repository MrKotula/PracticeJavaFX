package com.practice.projectitproger.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.practice.projectitproger.DB;
import com.practice.projectitproger.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FullArticleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button return_btn;

    @FXML
    private Label textArticle;

    @FXML
    private Label titleArticle;


    @FXML
    void returnToArticles(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HelloApplication.setScene("articles-panel.fxml", stage);
    }

    public static int idA;
    public static String titleA;
    public static String textA;

    @FXML
    void initialize() throws SQLException, IOException, ClassNotFoundException {

        DB db = new DB();
        ResultSet res = db.getArticle(ArticlesController.getIdArticle);

        while (res.next()) {
            titleArticle.setText(res.getString("title"));
            textArticle.setText(res.getString("text"));
        }
    }
}







