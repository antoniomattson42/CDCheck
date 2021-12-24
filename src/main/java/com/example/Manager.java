package com.example;

import java.net.URL;
import java.util.Scanner;

public class Manager {
    private String APIKey;
    private UrlCreator urlCreator;

    public Manager(String name) {
        Profile.getInstance();
        Profile.setSummonerName(name);
        APIKey = "RGAPI-30f3951e-68fb-4245-a027-467dd07d0c02";
        urlCreator = new UrlCreator(APIKey);
    }

    public String getMatch() {
        if (Profile.getSummonerID() == null) {
            getID();
        }
        String match = UrlContents.getUrlContents(urlCreator.summonerGame(Profile.getSummonerID()));
        System.out.println(match);
        System.out.println(ChampParser.getAbilityDescription(0, match, 0));
        return match;
//        parser = new ChampParser();
//        return parser.getChamp(urlCreator.summonerGame(), "name");
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
