package com.practice.projectitproger;

import java.sql.*;

public class DB {

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB_NAME = "projectitproger";
    private final String LOGIN = "root";
    private final String PASS = "root";

    private Connection dbConn = null;

        private Connection getDbConnection() throws ClassNotFoundException, SQLException {
            String str = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConn = DriverManager.getConnection(str, LOGIN, PASS);
            return dbConn;
        }

        public void isConnected() throws ClassNotFoundException, SQLException {
        dbConn = getDbConnection();
            System.out.println(dbConn.isValid(1000));
    }

        public void regUser(String login, String email, String password) throws ClassNotFoundException, SQLException{
            String str = "INSERT INTO `users` (`login`, `email`, `password`) VALUES (?, ?, ?)";
            PreparedStatement pdSt = getDbConnection().prepareStatement(str);
            pdSt.setString(1, login);
            pdSt.setString(2, email);
            pdSt.setString(3, password);
            pdSt.executeUpdate();
        }

        public boolean isExistUser(String login){
            String sql = "SELECT `id` FROM `users` WHERE `login` = ?";
            try {
               PreparedStatement prSt = getDbConnection().prepareStatement(sql);
                prSt.setString(1, login);
                ResultSet res = prSt.executeQuery();
                return res.next();
            } catch (SQLException | RuntimeException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        return false;
        }

    public boolean authUser(String login, String password) {
        String sql = "SELECT `id` FROM `users` WHERE `login` = ? AND `password` = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, login);
            prSt.setString(2, password);
            ResultSet res = prSt.executeQuery();
            return res.next();
        } catch (SQLException | RuntimeException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getArticles(){
        String sql = "SELECT `title`, `intro` FROM `articles`";
        Statement statement = null;
        try {
            statement = getDbConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addArticle(String title, String anons, String full_text) throws SQLException, ClassNotFoundException {
        String str = "INSERT INTO `articles` (`title`, `intro`, `text`) VALUES (?, ?, ?)";
        PreparedStatement pdSt = getDbConnection().prepareStatement(str);
        pdSt.setString(1, title);
        pdSt.setString(2, anons);
        pdSt.setString(3, full_text);
        pdSt.executeUpdate();
    }
}
