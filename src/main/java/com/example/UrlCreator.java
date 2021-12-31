package com.example;

public class UrlCreator {
    private String APIKey;

    public UrlCreator(String APIKey) {
        this.APIKey = APIKey;
    }

    public String summonerProfile(String name) {
        return "https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" +
                name +
                "?api_key=" +
                APIKey;
    }

    public String summonerGame(String id) {
        return "https://na1.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" +
                id +
                "?api_key=" +
                APIKey;
    }

    public static String getChampList() {
        return "http://ddragon.leagueoflegends.com/cdn/" + ChampParser.getVersion() + "/data/en_US/champion.json";
    }

    public static String getChampInfo(String champName) {
        return "http://ddragon.leagueoflegends.com/cdn/" + ChampParser.getVersion() + "/data/en_US/champion/" + champName + ".json";
    }

    public static String getRegionData() {
        return "https://ddragon.leagueoflegends.com/realms/na.json";
    }

    public static String getChampIcon(String id) {
        return "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/" +
                id +
                ".png";
    }
}
