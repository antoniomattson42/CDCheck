package com.example.cdcheck;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Manager manager = new Manager("Cold Cheesestick");
        UrlContents contents = new UrlContents();
        Label label = new Label();
        //label.setText(contents.getUrlContents("https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/Ilovemylasagna?api_key=RGAPI-e3be6291-120a-4eff-b4bf-4ee622f86bad"));
        label.setText(manager.getMatch());
        BorderPane pane = new BorderPane();
        pane.setCenter(label);
        Scene scene = new Scene(pane, 800, 500);
        stage.setTitle("CD Checker");
        Scene scene1 = new Scene(new StartScreen(), 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}