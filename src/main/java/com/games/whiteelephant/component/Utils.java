package com.games.whiteelephant.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class Utils {

    public JsonArray getGamesFromFile(String filePath) {
        // Read the file and parse it as JSON
        String content = readFile(filePath);
        if (content != null && !content.isEmpty()) {
            try {
                // Parse the content into a JsonObject using Gson
                JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

                // Check if the "games" key exists and is an array
                if (jsonObject.has("games")) {
                    return jsonObject.getAsJsonArray("games");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null; // Return null if no games found or error occurs
    }

    // Helper method to read the content of a file
    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
