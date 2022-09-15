module com.practice.projectitproger {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.practice.projectitproger to javafx.fxml;
    exports com.practice.projectitproger;
}