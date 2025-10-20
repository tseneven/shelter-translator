package com.shelter.translator.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shelter.api.Translator;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
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

            assert signedMessage != null;
            sendClientMessage(username, signedMessage.signedBody().content());

            return true;
        });
    }

    // getting the current client language
    public static String getCurrentClientLanguage(){
        MinecraftClient client = MinecraftClient.getInstance();

        if(client == null || client.getLanguageManager() == null){
            return Utils.toGoogleLang("en_us");
        }
        return Utils.toGoogleLang(client.getLanguageManager().getLanguage());
    }

    // displaying messages in chat
    public static void sendClientMessage(String username, String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {

            JsonArray translatorText = Translator.translator(message);
            String translatedText = "";


            if (translatorText == null || translatorText.size() == 0) {
                return;
            }

            if (translatorText.size() > 0) {
                JsonObject t = translatorText.get(0).getAsJsonObject();
                translatedText = t.get("text").getAsString();
                String detectedLang = t.get("detectedLanguageCode").getAsString();
                String curLang = getCurrentClientLanguage();
                if(curLang.equals(detectedLang)){
                    translatedText = null;
                }
            }

            if(translatedText != null && !translatedText.isEmpty()){
                Text text = Text.literal("TRANSLATOR: "+ username+ " " + translatedText).formatted(Formatting.DARK_GRAY, Formatting.ITALIC);
                client.inGameHud.getChatHud().addMessage(text);
            }

        }
    }

}