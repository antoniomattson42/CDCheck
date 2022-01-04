package com.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChampIcon extends ImageView {

    public ChampIcon(String spell, String name) throws FileNotFoundException {
        super(new Image(new FileInputStream("src/main/resources/icons/spell/" + name + spell + ".png")));
        setPreserveRatio(true);
        setFitHeight(75);
    }
}
