package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.*;

public class ChampParser {
    private static JSONObject champIdentifier = new JSONObject(UrlContents.getUrlContents(UrlCreator.getChampList()));

    private static String getChamp(String ID, String attribute) {
        String champAttribute = "Not found";
        JSONObject data = champIdentifier.getJSONObject("data");
        Iterator champList = data.keys();
        boolean check = true;
        while (champList.hasNext() && check) {
            JSONObject champData = data.getJSONObject((String) champList.next());
            String champKey = getValue(champData, "key");
            if (champKey.equals(ID)) {
                check = false;
                champAttribute = getValue(champData, "name");
            }
        }
        return champAttribute;
    }

    public static ArrayList<String> getTeamNames(int side, String match) {
        return getTeamAttribute(side, match, "summonerName");
    }

    private static ArrayList<String> getTeamChampID(int side, String match) {
        return getTeamAttribute(side, match, "championId");
    }

    public static ArrayList<String> getTeamChamps(int side, String match) {
        ArrayList<String> teamID = getTeamChampID(side, match);
        ArrayList<String> champList = new ArrayList<>();
        for (int count = 0; count < teamID.size(); count++) {
            champList.add(getChamp(teamID.get(count), "name"));
        }
        return champList;
    }

    public static String getAbilityDescription(int order, String match, int ability) {
        ArrayList<String> champs;
        if (order > 4) {
            order -= 5;
            champs = getTeamChamps(200, match);
        } else {
            champs = getTeamChamps(100, match);
        }
        JSONArray abilityData = new JSONArray(getChampData("spells", champs.get(order)));
        return abilityData.getJSONObject(ability).toString();
    }

    private static String getChampData(String attribute, String champName) {
        JSONObject champ = new JSONObject(UrlContents.getUrlContents(UrlCreator.getChampInfo(champName)));
        JSONObject data = champ.getJSONObject("data");
        JSONObject champData = data.getJSONObject(champName);
        String attributeData = champData.get(attribute).toString();
        return attributeData;
    }

    public static ArrayList<String> getTeamAttribute(int side, String match, String attribute) {
        ArrayList<String> list = new ArrayList<>();
        JSONArray teamArray = new JSONArray(getTeam(side, match));
        for (int count = 0; count < teamArray.length(); count++) {
            list.add(getValue(teamArray.getJSONObject(count), attribute));
        }
        return list;
    }

    private static String getTeam(int side, String match) {
        ArrayList<String> list = new ArrayList<>();
        JSONObject matchJSON = new JSONObject(match);
        JSONArray playerArray = matchJSON.getJSONArray("participants");
        for (int count = 0; count < playerArray.length(); count++) {
            if (getValue(playerArray.getJSONObject(count), "teamId").equals("" + side)) {
                list.add(playerArray.getJSONObject(count).toString());
            }
        }
        return list.toString();
    }

    private static String getValue(JSONObject jsonObject, String key) {
        String value;
        try {
            value = jsonObject.getJSONObject(key).toString();
        }
        catch (Exception e) {
            try {
                value = jsonObject.getString(key);
            }
            catch (Exception ee) {
                try {
                    value = jsonObject.getJSONArray(key).toString();
                }
                catch (Exception eee) {
                    value = "" + jsonObject.getInt(key);
                }
            }
        }
        return value;
    }

    public static String getVersion() {
        JSONObject realms = new JSONObject(UrlContents.getUrlContents(UrlCreator.getRegionData()));
        return getValue(realms, "v");
    }

    private static String getValue(JSONArray array, int key) {
        String value;
        try {
            value = array.getJSONObject(key).toString();
        }
        catch (Exception e) {
            try {
                value = array.getString(key);
            } catch (Exception ee) {
                try {
                    value = array.getJSONArray(key).toString();
                } catch (Exception eee) {
                    value = "" + array.getInt(key);
                }
            }
        }
        return value;
    }
}
