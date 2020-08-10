package com.JGG.AttendanceControl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/Login.fxml")));
        stage.setTitle("Login Window");
        stage.setScene(new Scene(root));
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
