package com.shelter.translator;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shelter_translator implements ModInitializer {
	public static final String MOD_ID = "shelter_translator";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		System.out.println("[Shelter Translator] Мод загружен (main)!");

	}
}