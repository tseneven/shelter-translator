package com.shelter.translator.chat;

import com.google.gson.JsonObject;
import com.shelter.api.Translator;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import com.shelter.Utils;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class ChatListener{

    private static boolean shown = false;

    // listener registration
    public void register(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!shown && client.world != null && client.player != null) {
                client.inGameHud.getChatHud().addMessage(
                        Text.literal("Mod by Neven\n" +
                                "Specially for Shelter!\n" +
                                "GitHub: https://github.com/tseneven/shelter-translator.git").formatted(Formatting.RED, Formatting.BOLD)
                );
                shown = true;
            }
        });

        ClientReceiveMessageEvents.ALLOW_CHAT.register((text, signedMessage, gameProfile, sender, type) -> {
            String fullText = text.getString();
            int firstSpace = fullText.indexOf(' ');
            String username;

            if (firstSpace != -1) {
                username = fullText.substring(0, firstSpace);
            } else {
                username = fullText;
            }

            username = username.replaceAll("[<>:]", "");

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return true;

            String myUsername = client.player.getGameProfile().getName();

            System.out.println(username);
            System.out.println(myUsername);

            if (myUsername.equals(username)) {
                return true;
            }

            sendClientMessage("<" + myUsername + ">", username, signedMessage.signedBody().content());


            return true;
        });
    }

    // getting the current server address
    public static String getCurrentServerAddress() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.getCurrentServerEntry() != null) {
            ServerInfo serverInfo = client.getCurrentServerEntry();

            String host = serverInfo.address;

            return host;
        }

        return null;
    }

    // getting the current client language
    public static String getCurrentClientLanguage(){
        MinecraftClient client = MinecraftClient.getInstance();

        if(client == null || client.getLanguageManager() == null){
            return Utils.toGoogleLang("en_us");
        }
        return Utils.toGoogleLang(client.getLanguageManager().getLanguage());
    }


    public static void sendClientMessage(String username, String ownerUsername, String message) {
        String curLang = getCurrentClientLanguage();
        String curServer = getCurrentServerAddress();

        Translator.translatorAsync(message, curLang, curServer, username, translations -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            if (translations.size() == 0) return;

            JsonObject first = translations.get(0).getAsJsonObject();
            String textToShowStr;
            Formatting color = Formatting.DARK_GRAY;
            boolean bold = false;

            if (first.has("error")) {
                String errorMessage = first.get("error").getAsString();

                if (errorMessage.equals("Доступ запрещён")) {
                    textToShowStr = "This mod can only be used on the shelter server: shelter.ru-mc.ru";
                } else if (errorMessage.equals("Доступ запрещён: вы не в списке бетатестеров")) {
                    textToShowStr = "You are not on the beta tester list.";
                }
                else {
                    textToShowStr = errorMessage;
                }

                color = Formatting.DARK_RED;
                bold = true;
            } else {
                String translatedText = first.get("text").getAsString();
                String detectedLang = first.get("detectedLanguageCode").getAsString();

                if (curLang.equals(detectedLang)) return;

                textToShowStr = translatedText;
            }

            Text textToShow = Text.literal("<"+ownerUsername+ ">" + " " + textToShowStr)
                    .formatted(color, bold ? Formatting.BOLD : Formatting.ITALIC);

            client.execute(() -> client.inGameHud.getChatHud().addMessage(textToShow));
        });
    }
}