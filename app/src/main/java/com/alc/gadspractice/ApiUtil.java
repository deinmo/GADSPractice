package com.alc.gadspractice;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil() {
    }

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";

    public static URL BuildUrl(String title) {

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon().appendQueryParameter("/api/", title).build();
        try {
            url = new URL(uri.toString());
            String message = url.toString();
            Log.d("message","Succesful");
            Log.d("message",message);
        } catch (MalformedURLException e) {
            Log.d("message","UnSuccesful");
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStream stream = connection.getInputStream()) {
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            Log.d("mymessage", "sucessfully");
            boolean hasdata = scanner.hasNext();
            if (hasdata) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        } finally {
            connection.disconnect();
        }
    }

    public static LeadersClass[] getleadersfromjson(String json) {
        final String NAME = "name";
        final String COUNTRY = "country";
        final String SCORE = "score";
        final String IMAGE = "badgeUrl";
        LeadersClass[] leadersClasses = new LeadersClass[20];
        try {
            JSONArray array = new JSONArray(json);
            int num = array.length();
            for(int i = 0; i < num; i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                LeadersClass leaders = new LeadersClass(jsonObject.getString(NAME),(jsonObject.getString(SCORE)),
                        jsonObject.getString(COUNTRY));
                leadersClasses[i] = leaders;
            }
        } catch (JSONException e) {
            Log.d("mymessage", "It was sucessful");
            e.printStackTrace();
        }
        return leadersClasses;
    }

    public static LeadersClass[] getlogesthoursfromjson(String json) {
        final String NAME = "name";
        final String COUNTRY = "country";
        final String HOURS = "hours";
        final String IMAGE = "badgeUrl";
        LeadersClass[] leadersClasses = new LeadersClass[20];
        try {
            JSONArray array = new JSONArray(json);
            int num = array.length();
            for(int i = 0; i < num; i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                LeadersClass leaders = new LeadersClass(jsonObject.getString(NAME),(jsonObject.getString(HOURS)),
                        jsonObject.getString(COUNTRY));
                Log.d("mymessage", "It was sucessful");
                leadersClasses[i] = leaders;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return leadersClasses;
    }

}