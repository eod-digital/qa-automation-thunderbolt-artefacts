package com.eod.digital.factory;

import com.eod.digital.util.FactoryUtil;

public class ThemeFactory {
    private static FactoryUtil factoryUtil;
    private final static String[] THEMES = new String[] {"Music", "Sports", "Lifestyle", "Movies", "News & Info", "Documentary", "Entertainment", "Comedy"};

    public ThemeFactory() {
        factoryUtil = new FactoryUtil();
    }

//    public static Theme createNewTheme() {
//        String title = getRandomTheme();
//        String colour = generateRandomThemeColour();
//        String index = String.valueOf(generateRandomThemeIndex());
//
//        return new Theme.Builder()
//                .setThemeTitle(title)
//                .setThemeIndex(index)
//                .setThemeColour(colour)
//                .build();
//    }

    private static String getRandomTheme() {
        return factoryUtil.getRandomString(THEMES);
    }
    private static String generateRandomThemeColour() {
        return factoryUtil.generateRandomHexadecimal(6);
    }
    private static Integer generateRandomThemeIndex() {
        return factoryUtil.generateRandomNumeral(1);
    }
}
