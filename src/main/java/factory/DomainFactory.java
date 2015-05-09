package factory;

import model.Domain;
import util.FactoryUtil;

public class DomainFactory {
    private static FactoryUtil factoryUtil = new FactoryUtil();
    private final static String[] EXTENSIONS = new String[] {"tv", "com", "fm", "net", "us", "video", "io", "co", "ws", "org"};

//    public Domain addNewDomain() {
//        String name = generateRandomDomainName();
//        String token = generateDomainToken();
//
//        return new Domain.Builder()
//                .setDomainName(name)
//                .setDescription("")
//                .setDomainToken(token)
//                .setActivation("true")
//                .build();
//    }

    private static String generateDomainToken() {
        return factoryUtil.generateRandomHexadecimal(16);
    }
    private static String generateRandomDomainName() {
        return factoryUtil.generateRandomAlphaNumeric(8) + "." + factoryUtil.getRandomString(EXTENSIONS);
    }
}
