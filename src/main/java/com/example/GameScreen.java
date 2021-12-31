package com.example;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class GameScreen extends GridPane {
    private final int RED = 200;
    private final int BLUE = 100;
    private Manager manager;
    private ArrayList<String> champsBlue;
    private ArrayList<String> champsRed;
    private ArrayList<String> namesBlue;
    private ArrayList<String> namesRed;
    private ArrayList<ImageView> iconsBlue;
    private ArrayList<ImageView> iconsRed;

    public GameScreen() {
        manager = new Manager();
        manager.getMatch(false);
        getArrays();
        ArrayList<Label> champsB = stringToLabel(champsBlue);
        ArrayList<Label> champsR = stringToLabel(champsRed);
        ArrayList<Label> namesB = stringToLabel(namesBlue);
        ArrayList<Label> namesR = stringToLabel(namesRed);
        for (int count = 0; count < champsBlue.size(); count++) {
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(iconsBlue.get(count));
            nodes.add(champsB.get(count));
            nodes.add(namesB.get(count));
            nodes.add(namesR.get(count));
            nodes.add(champsR.get(count));
            nodes.add(iconsRed.get(count));
            for (int loop = 0; loop < nodes.size(); loop++) {
                add(nodes.get(loop), loop, count);
                GridPane.setHalignment(nodes.get(loop), HPos.CENTER);
//                GridPane.setValignment(nodes.get(loop), VPos.CENTER);
            }
        }
        ColumnConstraints imageRestraints = new ColumnConstraints();
        ColumnConstraints labelRestraints = new ColumnConstraints(125);
        labelRestraints.setHalignment(HPos.CENTER);
        getColumnConstraints().addAll(imageRestraints, labelRestraints, labelRestraints, labelRestraints, labelRestraints, imageRestraints);
        setAlignment(Pos.CENTER);
        setGridLinesVisible(true);
    }

    private void getArrays() {
        champsBlue = manager.getChamps(BLUE);
        champsRed = manager.getChamps(RED);
        namesBlue = manager.getNames(BLUE);
        namesRed = manager.getNames(RED);
        iconsBlue = manager.getIcons(BLUE);
        iconsRed = manager.getIcons(RED);
        fixImageSize();
    }

    private ArrayList<Label> stringToLabel(ArrayList<String> list) {
        ArrayList<Label> labelList = new ArrayList<>();
        for (String s : list) {
            Label label = new Label(s);
            label.setMinWidth(125);
            labelList.add(label);
        }
        return labelList;
    }

    private void fixImageSize() {
        for (int count = 0; count < iconsRed.size(); count++) {
            iconsBlue.get(count).setPreserveRatio(true);
            iconsRed.get(count).setPreserveRatio(true);
            iconsBlue.get(count).setFitHeight(50);
            iconsRed.get(count).setFitHeight(50);
        }
    }
}
