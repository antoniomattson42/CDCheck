package com.example.cdcheck;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StartScreen extends BorderPane{

    public StartScreen() {
        Label helpText = new Label();
        helpText.setText("Please Enter Your name in the Text Field below");
        TextField nameField = new TextField();
        nameField.setMaxWidth(80);
        Button startButton = new Button();
        startButton.setText("Start");
        startButton.setOnAction(e -> {
            //screenController.changeScreen(Screen.GAME);
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(helpText, nameField, startButton);
        Insets insets = new Insets(0, 0, 30, 0);
        helpText.setPadding(insets);
        nameField.setPadding(insets);
        startButton.setPadding(insets);
        setCenter(vBox);
    }
}
