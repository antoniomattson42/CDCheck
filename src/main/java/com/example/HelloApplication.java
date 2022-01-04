package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class HelloApplication extends Application {
    private static Screen currentScreen;
    private static Scene mainScene;


    @Override
    public void start(Stage stage) {
        stage.setTitle("CD Checker");
        mainScene = new Scene(new BorderPane(), 900, 620);
        changeScreen(Screen.START, null);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void changeScreen(Screen screen, String id) {
        switch (screen) {
            case GAME:
                currentScreen = Screen.GAME;
                mainScene.setRoot(new GameScreen());
                break;
            case START:
                currentScreen = Screen.START;
                mainScene.setRoot(new StartScreen());
                break;
            case CHAMP:
                currentScreen = Screen.GAME;
                mainScene.setRoot(new ChampScreen(id));
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}