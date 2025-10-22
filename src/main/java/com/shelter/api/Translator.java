package com.shelter.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Translator {

    public static void translatorAsync(String text, String targetLanguageCode, String curServer, String username, TranslationCallback callback) {
        try {


            String requestBody = "{"
                    + "\"serverAddress\": \"" + curServer + "\","
                    + "\"nickname\": \"" + username + "\","
                    + "\"texts\": [\"" + text + "\"],"
                    + "\"targetLanguageCode\": \"" + targetLanguageCode + "\""
                    + "}";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://147.45.108.225:5000/api/Retranslator/retrans"))
                    .header("Content-Type", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        try {
                            int status = response.statusCode();
                            String body = response.body();

                            if (status != 200) {
                                if (body != null && !body.isEmpty()) {
                                    JsonArray errorArray = new JsonArray();
                                    JsonObject errorObj = new JsonObject();
                                    errorObj.addProperty("error", body);
                                    errorArray.add(errorObj);
                                    callback.onTranslation(errorArray);
                                } else {
                                    callback.onTranslation(new JsonArray());
                                }
                                return;
                            }

                            JsonObject root = JsonParser.parseString(body).getAsJsonObject();
                            JsonArray translations = root.getAsJsonArray("translations");
                            callback.onTranslation(translations);

                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onTranslation(new JsonArray());
                        }
                    })
                    .exceptionally(e -> {
                        e.printStackTrace();
                        JsonArray errorArray = new JsonArray();
                        JsonObject errorObj = new JsonObject();
                        errorObj.addProperty("error", e.getMessage());
                        errorArray.add(errorObj);
                        callback.onTranslation(errorArray);
                        return null;
                    });

        } catch (Exception e) {
            e.printStackTrace();
            JsonArray errorArray = new JsonArray();
            JsonObject errorObj = new JsonObject();
            errorObj.addProperty("error", e.getMessage());
            errorArray.add(errorObj);
            callback.onTranslation(errorArray);
        }
    }

    public interface TranslationCallback {
        void onTranslation(JsonArray translations);
    }
}
