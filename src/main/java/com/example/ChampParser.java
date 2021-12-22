package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.ClosedFileSystemException;
import java.util.Iterator;

import org.json.*;

public class ChampParser {
    private String jsonString;
    private JSONObject champIdentifier;

    public ChampParser() {
        this.jsonString = UrlContents.getUrlContents(UrlCreator.getChampList());
        champIdentifier = new JSONObject(jsonString);
    }

    public String getChamp(String ID, String attribute) {
        Iterator iter = champIdentifier.keys();
        String tempy = (String)iter.next();
        System.out.println(tempy);
        JSONObject data = champIdentifier.getJSONObject(tempy);
        Iterator champList = data.keys();
        while (champList.hasNext()) {
            JSONObject champData = data.getJSONObject((String)champList.next());
            System.out.println(champData.toString());
            //Find the ID key in the list
            Iterator champKeyList = champData.keys();
            String champKey = "";
            boolean check = true;
            while (champKeyList.hasNext() || check) {
                champKey = (String)champKeyList.next();
                if (champKey.equals(ID)) {
                    check = false;
                }
            }
            if (!check) {
                System.out.println(champKey);
                System.out.println(champData.getString(champKey));
            }
            JSONObject champID = .getJSONObject("name");
            if (ID.equals(champID.toString())) {
                System.out.println(temp.getString("id"));
                return temp.getString("id");
            }
        }
        System.out.println("NOT FOUND");
        return "NOT FOUND";
    }

}
