package com.example;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class ChampView extends Button {
    private String champID;
    private ImageView champIcon;


    public ChampView(String champID, ImageView imageView) {
        super();
        this.champID = champID;
        champIcon = imageView;
        champIcon.setPreserveRatio(true);
        champIcon.setFitHeight(50);
        setGraphic(champIcon);
        setMaxSize(50,50);
        setStyle("-fx-border-color: transparent");
        Tooltip tooltip = new Tooltip("Go to Champ Info");
        setTooltip(tooltip);
        setOnAction(event -> {
            HelloApplication.changeScreen(Screen.CHAMP, champID);
        });
    }
}
