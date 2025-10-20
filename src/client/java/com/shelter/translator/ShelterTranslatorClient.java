package com.shelter.translator;

import com.shelter.translator.chat.ChatListener;
import net.fabricmc.api.ClientModInitializer;

public class ShelterTranslatorClient implements ClientModInitializer {
	private ChatListener chatListener;

	@Override
	public void onInitializeClient() {
		chatListener = new ChatListener();
		System.out.println("[Shelter Translator] Мод загружен (client)!");
		chatListener.register();
	}
}