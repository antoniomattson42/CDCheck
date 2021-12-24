package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Manager manager = new Manager("theasiankookie");
            UrlContents contents = new UrlContents();
            Label label = new Label();
            label.setText(manager.getMatch());
            BorderPane pane = new BorderPane();
            pane.setCenter(label);
            Scene scene = new Scene(pane, 800, 500);
            stage.setTitle("CD Checker");
            Scene scene1 = new Scene(new StartScreen(), 800, 500);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}