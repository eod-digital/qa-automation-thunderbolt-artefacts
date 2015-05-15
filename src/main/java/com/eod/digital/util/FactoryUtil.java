package com.eod.digital.util;

import java.util.Random;

public class FactoryUtil {
    private Random random = new Random();
    private String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    private String NUMERAL = "0123456789";
    private String ALPHANUMERIC = ALPHABETS + NUMERAL;
    private String HEXADECIMAL = "ABCDEF0123456789";
    private char[] chars;

    public String getRandomString(String[] strings) {
        return String.valueOf(strings[random.nextInt(strings.length)]);
    }

    public String generateRandomAlphaNumeric(int length) {
        chars = ALPHANUMERIC.toCharArray();
        return generateRandomString(chars, length);
    }

    public String generateRandomHexadecimal(int length) {
        chars = HEXADECIMAL.toCharArray();
        return generateRandomString(chars, length);
    }

    public Integer generateRandomNumeral(int length) {
        chars = NUMERAL.toCharArray();
        return Integer.valueOf(generateRandomString(chars, length));
    }

    private String generateRandomString(char[] chars, int length) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i <= length; i++) {
            char letter = chars[random.nextInt(chars.length)];
            string.append(letter);
        }
        return String.valueOf(string);
    }
}
