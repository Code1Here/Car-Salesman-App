module Java.Final {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens com.test;
    opens com.test.controller; // These classes control the fxml. view doesn't need to be here
}