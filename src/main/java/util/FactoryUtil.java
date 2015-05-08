package util;

import java.util.Random;

public class FactoryUtil {
    private Random random = new Random();

    public String getRandomString(String[] strings) {
        return String.valueOf(strings[random.nextInt(strings.length)]);
    }
}
