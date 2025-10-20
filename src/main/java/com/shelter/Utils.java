package com.shelter;



public class Utils {

    //Converts Minecraft language to Yandex Translate format

    public static String toGoogleLang(String mcLangCode) {
        if (mcLangCode == null) return "en";

        mcLangCode = mcLangCode.toLowerCase();

        System.out.println("Язык клиента: " + mcLangCode);

        return switch (mcLangCode) {
            case "zh_cn" -> "zh-CN";
            case "zh_tw" -> "zh-TW";
            case "pt_br" -> "pt-BR";
            default -> mcLangCode.split("_")[0];
        };
    }
}