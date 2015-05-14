package factory;

import model.User;
import util.FactoryUtil;

public class UserFactory {
    private static FactoryUtil factoryUtil;

    private final static String[] FIRST_NAME = new String[]{"Peter", "Naif", "Shu'ayb", "Sarah", "Leanna", "Theodoric", "Chukwubuikem", "Udobata", "Narciso", "Adelfo", "Sha'ban", "Lubomierz"};
    private final static String[] LAST_NAME = new String[]{"Dyer", "Haddad", "Czerwinski", "Kruger", "Kuefer", "Paterson", "T'ang", "Chukwumoge", "Onyemauchechukwu", "Barros", "Lai", "Yamauchi"};
    private final static String EMAIL = "@test-mail.com";

    public UserFactory() {
       factoryUtil = new FactoryUtil();
    }

//    public static User addNewUser() {
//        String firstName = getFirstName();
//        String lastName = getLastName();
//        String email = getEmail();
//        String username = getUsername();
//
////        return new User.Builder()
////            .setFirstName(firstName)
////            .setLastName(lastName)
////            .setEmail(email)
////            .setUsername(username)
////            .setPassword("P@55w0rd!")
////            .setDomain("domain")
////            .setRole("role")
////            .build();
//    }

    private static String getFirstName() { return factoryUtil.getRandomString(FIRST_NAME); }
    private static String getLastName() { return factoryUtil.getRandomString(LAST_NAME); }
    private static String getUsername()  { return factoryUtil.generateRandomAlphaNumeric(8); }
//    private static String getEmail()  { return firstName.replace("'","").toLowerCase() + "." + lastName.replace("'","").toLowerCase() + EMAIL; }
}
