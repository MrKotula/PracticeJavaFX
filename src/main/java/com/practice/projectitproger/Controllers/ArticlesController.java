package com.practice.projectitproger.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import com.practice.projectitproger.DB;
import com.practice.projectitproger.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArticlesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button log_out, add_article;

    @FXML
    private VBox vBox;

    public static int getIdArticle;

    @FXML
    void initialize() throws SQLException, IOException {

        DB db = new DB();
        ResultSet resultSet = db.getArticles();

        while (resultSet.next()) {
            Node node = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("article.fxml")));

            Label title = (Label) node.lookup("#title");
            title.setText(resultSet.getString("title"));

            Label intro = (Label) node.lookup("#intro");
            intro.setText(resultSet.getString("intro"));

            //даю ід всім елементам в resultSet
            node.setId(resultSet.getString("id"));

            final Node nodeSet = node;

            node.setOnMouseEntered(event -> {
                node.setStyle("-fx-background-color: #707173");
            });
            node.setOnMouseExited(event -> {
                node.setStyle("-fx-background-color: #343434");
            });
            node.setOnMousePressed(event -> {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                try {
                    getIdArticle = Integer.parseInt(nodeSet.getId());
                    HelloApplication.setScene("full_article.fxml", stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


            vBox.getChildren().add(node);
            vBox.setSpacing(15);

        }

        log_out.setOnAction(event ->
        {
            try {
                exit(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        add_article.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                HelloApplication.setScene("add_article.fxml", stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }

    private void exit(ActionEvent event) throws IOException {
        File file = new File("user.settings");
        file.delete();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HelloApplication.setScene("mainProject.fxml", stage);
    }

}
