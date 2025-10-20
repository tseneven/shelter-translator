package com.shelter;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static Dotenv dotenv;

    public static final String API_KEY;
    public static final String FOLDER_ID;

    static {
        String apiKeyTemp = null;
        String folderIdTemp = null;

        try {
            dotenv = Dotenv.load();
            apiKeyTemp = dotenv.get("API_KEY");
            folderIdTemp = dotenv.get("FOLDER_ID");
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке .env файла: " + e.getMessage());
            e.printStackTrace();
        }

        API_KEY = apiKeyTemp;
        FOLDER_ID = folderIdTemp;
    }
}
