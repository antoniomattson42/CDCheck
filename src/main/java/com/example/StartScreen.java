package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StartScreen extends BorderPane {

    public StartScreen() {
        Label helpText = new Label();
        helpText.setText("Please Enter Your name in the Text Field below");
        TextField nameField = new TextField();
        Label warningLabel = new Label();
        nameField.setMaxWidth(100);
        nameField.setMaxHeight(1);
        Button startButton = new Button();
        startButton.setPrefWidth(50);
        startButton.setPrefHeight(25);
        startButton.setText("Start");
        startButton.setOnAction(e -> {
            String name = nameField.getText();
            Manager manager = new Manager();
            if (!name.equals("")) {
                warningLabel.setText(manager.setName(name));
                if (name.equals(warningLabel.getText())) {
                    if (Profile.getSummonerName().equals(name)) {
                        if (manager.getMatch(true).equals("")) {
                            warningLabel.setText("Summoner is not currently in a game");
                        } else {
                            HelloApplication.changeScreen(Screen.GAME);
                            System.out.println("CHANGE TO GAME SCREEN");
                        }
                    }
                }
            }
        });
        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(helpText, nameField, startButton, warningLabel);
        vBox.setAlignment(Pos.CENTER);
        setCenter(vBox);
    }
}
