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
        mainScene = new Scene(new BorderPane(), 800, 500);
        changeScreen(Screen.START);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void changeScreen(Screen screen) {
        switch (screen) {
            case GAME:
                currentScreen = Screen.GAME;
                mainScene.setRoot(new GameScreen());
                break;
            case START:
                currentScreen = Screen.START;
                mainScene.setRoot(new StartScreen());
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}