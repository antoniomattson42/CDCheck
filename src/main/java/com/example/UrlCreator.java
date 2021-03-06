package com.example.cdcheck;
import java.net.*;
import java.io.*;

public class UrlCreator {
    private String APIKey;

    public UrlCreator(String APIKey) {
        this.APIKey = APIKey;
    }

    public String summonerProfile(String name) {
        String summonerURL = "https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" +
                name +
                "?api_key=" +
                APIKey;
        return summonerURL;
    }

    public String summonerGame(String id) {
        String gameURL = "https://na1.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" +
                id +
                "?api_key=" +
                APIKey;
        return gameURL;
    }
}
