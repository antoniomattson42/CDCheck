package com.example;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    private String APIKey;
    private UrlCreator urlCreator;
    private String match;

    public Manager() {
        Profile.getInstance();
        APIKey = "RGAPI-30f3951e-68fb-4245-a027-467dd07d0c02";
        urlCreator = new UrlCreator(APIKey);
    }

    public ArrayList<ArrayList<String>> getAbilitiesInfo(String ID) {
        ArrayList<ArrayList<String>> infoList = new ArrayList<>();
        for (int count = 0; count < 4; count++) {
            infoList.add(ChampParser.getAbilityInfo(ID, count));
        }
        return infoList;
    }

    public ArrayList<String> getAbilitiesInfoAttributes() {
        return ChampParser.getAbilityInfoAttributes();
    }

    public ArrayList<ImageView> getAbilityIcons(String ID) {
        ArrayList<ImageView> icons = new ArrayList<>();
        String name = ChampParser.getName(ID);
        name = name.replace(" ", "");
        if (name.equals("Kai'Sa")) {
            name = "Kaisa";
        }
        try {
            icons.add(new ChampIcon("Q", name));
            icons.add(new ChampIcon("W", name));
            icons.add(new ChampIcon("E", name));
            icons.add(new ChampIcon("R", name));
        }
        catch (Exception e) {
            System.out.println(name + " spell not found");
            e.printStackTrace();
        }
        return icons;
    }

    public ImageView getSplash(String ID) {
        ImageView splash = new ImageView(UrlCreator.getChampSplash(ID));
        splash.setPreserveRatio(true);
        splash.setFitHeight(200);
        return splash;
    }

    public String setName(String name) {
        String check = checkName(name);
        if (check.equals(name)) {
            Profile.setSummonerName(check);
        }
        return check;
    }

    private String checkName(String name) {
        String profile = UrlContents.getUrlContents(urlCreator.summonerProfile(name));
        if (profile.equals("")) {
            return "Summoner not found, please enter a new one";
        }
        return name;

    }

    public String getMatch(boolean resetID) {
        if (Profile.getSummonerID() == null || resetID) {
            getID();
        }
        String match = UrlContents.getUrlContents(urlCreator.summonerGame(Profile.getSummonerID()));
        this.match = match;
        return match;
    }

    public ArrayList<String> getChamps(int side) {
        return ChampParser.getTeamChamps(side, match);
    }

    public ArrayList<String> getNames(int side) {
        return ChampParser.getTeamNames(side, match);
    }

    public ArrayList<Button> getIcons(int side) {
        return ChampParser.getTeamIcons(side, match);
    }

    public String getID() {
        String text = UrlContents.getUrlContents(urlCreator.summonerProfile(Profile.getSummonerName()));
        String id = parseFor(text, "id");
        Profile.setSummonerID(id);
        return id;
    }

    private String parseFor(String contents, String value) {
        Scanner scan = new Scanner(contents);
        scan.useDelimiter(":|,");
        boolean check = true;
        String string = "";
        while (check && scan.hasNext()) {
            string = scan.next();
            string = string.replace("\"", "");
            string = string.replace("{", "");
            string = string.replace("}", "");
            if (value.equals(string)) {
                check = false;
            }
        }
        if (!check) {
            string = scan.next();
            string = string.replace("\"", "");
            string = string.replace("{", "");
            string = string.replace("}", "");
            return string;
        }
        return "PARSE HAS FAILED";
    }
}
