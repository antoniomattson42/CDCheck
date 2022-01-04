package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ChampScreen extends BorderPane {
    private ArrayList<ImageView> champImages;
    private ImageView champSplash;
    private GridPane centerPane;
    private Manager manager;
    private ArrayList<ArrayList<String>> abilityInfo;
    private ArrayList<VBox> vBoxList;
    private ArrayList<String> attributes;
    private ColumnConstraints constraints;


    public ChampScreen(String ID) {
        manager = new Manager();
        champSplash = manager.getSplash(ID);
        abilityInfo = manager.getAbilitiesInfo(ID);
        champImages = manager.getAbilityIcons(ID);
        attributes = manager.getAbilitiesInfoAttributes();
        centerPane = new GridPane();
        constraints = new ColumnConstraints();
        constraints.setPercentWidth(25);
        BorderPane.setAlignment(champSplash, Pos.CENTER);
        setTop(champSplash);
        fixAttributes();
        makeLabels();
        setCenterPane();
    }

    private void makeLabels() {
        vBoxList = new ArrayList<>();
        for (int count = 0; count < abilityInfo.size(); count++) {
            VBox labels = new VBox();
            labels.setSpacing(10);
            labels.getChildren().add(champImages.get(count));
            for (int loop = 0; loop < abilityInfo.get(count).size() - 1; loop++) {
                VBox titleDescription = new VBox();
                titleDescription.getChildren().addAll(new Label(attributes.get(loop) + ":"),
                        stringResize(removeSymbols(abilityInfo.get(count).get(loop))));
                titleDescription.setAlignment(Pos.CENTER);
                labels.getChildren().add(titleDescription);
            }
            labels.setAlignment(Pos.TOP_CENTER);
            labels.setPadding(new Insets(10, 0, 0, 0));
            vBoxList.add(labels);
        }
    }

    private void setCenterPane() {
        for (int count = 0; count < vBoxList.size(); count++) {
            centerPane.add(vBoxList.get(count), count, 0);
            centerPane.getColumnConstraints().add(constraints);
        }
        centerPane.setAlignment(Pos.CENTER);
        setCenter(centerPane);
    }

    private VBox stringResize(String info) {
        Scanner scan = new Scanner(info);
        VBox labels = new VBox();
        while (scan.hasNext()) {
            StringBuilder builder = new StringBuilder();
            while (builder.toString().length() < 35 && scan.hasNext()) {
                builder.append(scan.next() + " ");
            }
            labels.getChildren().add(new Label(builder.toString()));
        }
        labels.setAlignment(Pos.CENTER);
        return labels;
    }

    private void fixAttributes() {
        for (int count = 0; count < attributes.size(); count++) {
            attributes.set(count, attributes.get(count).substring(0, 1).toUpperCase(Locale.ROOT) +
                    attributes.get(count).substring(1));
            if (attributes.get(count).contains("Burn")) {
                attributes.set(count, attributes.get(count).replace("Burn", ""));
            }
        }
    }

    private String removeSymbols(String info) {
        while (info.contains("<") || info.contains("{{")) {
            if (info.contains("<")) {
                info = info.substring(0, info.indexOf("<")).concat(info.substring(info.indexOf(">") + 1));
            }
            if (info.contains("{{")) {
                info = info.substring(0, info.indexOf("{{")).concat(info.substring(info.indexOf("}}") + 2));
            }
        }
        return info;
    }

    //    {"cooldownBurn":"16/15.5/15/14.5/14","image":{"w":48,"sprite":"spell13.png","x":96,"h":48,"y":0,"full":"TristanaE.png",
//    "group":"spell"},"cost":[50,55,60,65,70],"datavalues":{},"maxammo":"-1","leveltip":{"effect":["{{ cooldown }} ->
//    {{ cooldownNL }}","{{ cost }} -> {{ costNL }}","{{ passivebasedamage }} -> {{ passivebasedamageNL }}",
//    "{{ activebasedamage }} -> {{ activebasedamageNL }}","{{ activebadratio*100.000000 }}% -> {
//    { activebadrationl*100.000000 }}%"],"label":["Cooldown","@AbilityResourceName@ Cost","Passive Explosion Damage",
//    "Base Charge Damage","Attack Damage"]},"resource":"{{ cost }} {{ abilityresourcename }}"
//    ,"rangeBurn":"550",
//    "tooltip":"Passive: Tristana's Attacks that kill enemies deal
//     magic damage to surrounding enemies.
//   >Active:<\/spellActive> Tristana attaches a bomb to an enemy or turret that deals <physicalDamage>
//    {{ activedamage }} physical damage<\/physicalDamage> to surrounding enemies after {{ activeduration }} seconds.
//    The damage is increased by {{ critchanceamp*100 }}% Critical Strike Chance and by {{ activeperstackamp*100 }}%
//    each time Tristana hits an Attack or Ability (max 4 stacks).<br /><br />At {{ activemaxstacks }} stacks, the bomb
//    explodes immediately (max <physicalDamage>{{ activemaxdamage }} physical damage<\/physicalDamage>).",
//    "description":
//    "When Tristana kills a unit, her cannonballs burst into shrapnel, dealing damage to surrounding enemies. Can be
//    activated to place a bomb on a target enemy that explodes after a short duration dealing damage to surrounding units.
//    ","range":[550,550,550,550,550],"maxrank":5,"effect":[null,[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]
//    ,[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]],"costType":" {{ abilityresourcename }}","name":
//    "Explosive Charge","cooldown":[16,15.5,15,14.5,14],"id":"TristanaE","costBurn":"50/55/60/65/70","vars"
//    :[],"effectBurn":[null,"0","0","0","0","0","0","0","0","0","0"]}
}
