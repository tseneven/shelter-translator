package com.shelter.api;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shelter.Config;


public class Translator {

    // POST request to Yandex servers
        public static JsonArray translator(String text) {
            try {
                String apiKey = Config.API_KEY;

                String folderId =  Config.FOLDER_ID;

                String requestBody = "{"
                        +"\"folderId\": \"" + folderId + "\","
                        + "\"texts\": [\"" + text + "\"],"
                        + "\"targetLanguageCode\": \"ru\""
                        + "}";

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://translate.api.cloud.yandex.net/translate/v2/translate"))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Api-Key " + apiKey)
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                Gson gson = new Gson();
                JsonObject root = gson.fromJson(response.body(), JsonObject.class);
                JsonArray translations = root.getAsJsonArray("translations");
                return translations;


            } catch (Exception e) {
                e.printStackTrace();
                return new JsonArray();
            }
        }

    }


