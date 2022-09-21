package com.practice.projectitproger;

import com.practice.projectitproger.Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {

        DB db = new DB();
        File file = new File("user.settings");
        if(file.exists()) {

            FileInputStream fis = new FileInputStream("user.settings");
            ObjectInputStream ois = new ObjectInputStream(fis);
            User user = (User) ois.readObject();
            ois.close();

            if (db.isExistUser(user.getLogin()))
                setScene("articles-panel.fxml", stage);
            else
                setScene("mainProject.fxml", stage);
        }
        else
            setScene("mainProject.fxml", stage);
    }

    public static void main(String[] args) {
        launch();
    }


    public static void setScene(String nameScene, Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(nameScene));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("ProjectItProger!");
        stage.setScene(scene);
        stage.show();
    }

}