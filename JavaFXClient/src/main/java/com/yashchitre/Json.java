package com.yashchitre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Json {

    private static String wikiDomain = "https://en.wikipedia.org/";
    private static String response = "";
    private static ObservableList<QueryItem> result;

    public static ObservableList<QueryItem> fetch(String searchTerm) {

        String cleanedSearchTerm = searchTerm.replaceAll("[ ]+", "%20");

        try
        {
            String request = wikiDomain + "w/api.php?action=query&list=search&srsearch=" + cleanedSearchTerm
                    + "&srlimit=100&format=json";
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            int responseCode = conn.getResponseCode();
            System.out.println("Response code is: " + responseCode);

            if(responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " + responseCode);

            else
            {
                Scanner sc = new Scanner(url.openStream());
                response = sc.nextLine();
                sc.close();
            }

            JSONParser parse = new JSONParser();

            JSONObject jsonObject = (JSONObject)parse.parse(response);

            JSONObject query = (JSONObject) jsonObject.get("query");
            JSONArray search = (JSONArray) query.get("search");

            result = FXCollections.observableArrayList();

            for(Object item: search) {
                String title = (String) ((JSONObject)item).get("title");
                long pageId = (Long) ((JSONObject)item).get("pageid");
                String snippet = (String) ((JSONObject)item).get("snippet");
                snippet = snippet.replaceAll("<.*?>|&quot;", "");

                String content = String.join(" ", "Title:", title, "\n\n\t", snippet, "...");

                QueryItem queryItem = new QueryItem(pageId, content);
                result.add(queryItem);
            }

            conn.disconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static String getUrlByPageId(long pageId) {
        return wikiDomain + "?curid=" + pageId;
    }
}

