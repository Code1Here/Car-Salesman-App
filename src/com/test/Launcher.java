package com.test;

import com.test.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Objects;
public class Launcher extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
//        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/login.fxml")));
////        Parent parent = FXMLLoader.load(getClass().getResource("view/login.fxml"));
//
//        Scene scene = new Scene(parent);
//        stage.setScene(scene);;
//        stage.show();

        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showLoginWindow();

    }
}