package com.shelter.translator.chat;

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
        String curLang = getCurrentClientLanguage();
        Translator.translatorAsync(message, curLang, translations -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || translations.size() == 0) return;

            JsonObject t = translations.get(0).getAsJsonObject();
            String translatedText = t.get("text").getAsString();
            String detectedLang = t.get("detectedLanguageCode").getAsString();

            if (curLang.equals(detectedLang)) return;

            Text textToShow = Text.literal(username+ " "  + translatedText).formatted(Formatting.DARK_GRAY, Formatting.ITALIC);

            client.execute(() -> client.inGameHud.getChatHud().addMessage(textToShow));
        });
    }
}