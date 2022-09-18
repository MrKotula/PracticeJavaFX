module com.practice.projectitproger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.practice.projectitproger to javafx.fxml;
    exports com.practice.projectitproger;
    exports com.practice.projectitproger.Controllers;
    opens com.practice.projectitproger.Controllers to javafx.fxml;
}