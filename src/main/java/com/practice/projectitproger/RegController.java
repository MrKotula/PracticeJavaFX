package com.practice.projectitproger;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegController {

    DB db = new DB();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField auth_log;

    @FXML
    private PasswordField auth_password;

    @FXML
    private Button logInto;

    @FXML
    private CheckBox reg_checkBox;

    @FXML
    private TextField reg_email;

    @FXML
    private TextField reg_login;

    @FXML
    private PasswordField reg_password;

    @FXML
    private Hyperlink reg_terms;

    @FXML
    private Button toRegister;

    @FXML
    void initialize() {

        toRegister.setOnAction(event ->
                registrationUser());
        logInto.setOnAction(event ->
                authUser());
    }

    private void registrationUser() {
        String login = reg_login.getCharacters().toString();
        String email = reg_email.getCharacters().toString();
        String password = reg_password.getCharacters().toString();

        reg_login.setStyle("-fx-border-color: #fafafa");
        reg_email.setStyle("-fx-border-color: #fafafa");
        reg_password.setStyle("-fx-border-color: #fafafa");

        if (login.length() <= 1)
            reg_login.setStyle("-fx-border-color: #e06249");
        else if (email.length() <= 5 || !email.contains("@") || !email.contains("."))
            reg_email.setStyle("-fx-border-color: #e06249");
        else if (password.length() <= 3)
            reg_password.setStyle("-fx-border-color: #e06249");
        else if (!reg_checkBox.isSelected())
            toRegister.setText("Check the box");
        else if (db.isExistUser(login))
            toRegister.setText("Change your login!");
        else {
            try {
                db.regUser(login, email, md5String(password));
                reg_login.setText("");
                reg_email.setText("");
                reg_password.setText("");
                toRegister.setText("All right! :)");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String md5String(String pass) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pass.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        String m5dHex = bigInteger.toString(16);

        while (m5dHex.length() < 32)
            m5dHex = "0" + m5dHex;
        return m5dHex;
    }

    private void authUser() {
        String login = auth_log.getCharacters().toString();
        String password = auth_password.getCharacters().toString();

        auth_log.setStyle("-fx-border-color: #fafafa");
        auth_password.setStyle("-fx-border-color: #fafafa");

        if(login.length() <= 1)
            auth_log.setStyle("-fx-border-color: #e06249");
        else if(password.length() <= 3)
            auth_password.setStyle("-fx-border-color: #e06249");
        else if(!db.authUser(login, md5String(password)))
            logInto.setText("Change data!");
        else {
            auth_log.setText("");
            auth_password.setText("");
            logInto.setText("All right! :)");
        }
    }
}
