package com.example;
import java.util.List;

/**
 * Class to hold all the data related to the summoner and players in game
 */
public class Profile {
    private static String summonerName;
    private static String summonerID;
    private static Profile singleInstance = null;
    private List<String> players;

    /**
     * Private Constructor so there is only one instance of a profile
     */
    private Profile() {}

    /**
     * Gets the singleInstance of Profile, or instantiates it
     * @return the instance of Profile
     */
    public static Profile getInstance() {
        if (singleInstance == null) {
            singleInstance = new Profile();
        }
        return singleInstance;
    }

    /**
     * Sets the name of the summoner
     * @param name the new name
     */
    public static void setSummonerName(String name) {
        summonerName = name;
    }

    /**
     * Gets the name of the summoner
     * @return the name of the summoner
     */
    public static String getSummonerName() {
        return summonerName;
    }

    /**
     * Gets the id of the summoner
     * @return the summonerID
     */
    public static String getSummonerID() {
        return summonerID;
    }

    /**
     * Sets the summonerID
     * @param ID ID of the summoner
     */
    public static void setSummonerID(String ID) {
        summonerID = ID;
    }
}
