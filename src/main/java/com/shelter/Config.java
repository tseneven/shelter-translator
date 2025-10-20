package com.shelter;

import io.github.cdimascio.dotenv.Dotenv;

//Extracts variables from .env

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String API_KEY = dotenv.get("API_KEY");
    public static final String FOLDER_ID = dotenv.get("FOLDER_ID");
}