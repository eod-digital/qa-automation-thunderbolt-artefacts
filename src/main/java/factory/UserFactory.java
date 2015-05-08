package factory;

import util.FactoryUtil;

public class UserFactory {
    private static FactoryUtil factoryUtil = new FactoryUtil();

    private static String[] FIRST_NAME = {"Peter", "Naif", "Shu'ayb", "Sarah", "Leanna", "Theodoric", "Chukwubuikem", "Udobata", "Narciso", "Adelfo", "Sha'ban", "Lubomierz"};
    private static String[] LAST_NAME = {"Dyer", "Haddad", "Czerwinski", "Kruger", "Kuefer", "Paterson", "T'ang", "Chukwumoge", "Onyemauchechukwu", "Barros", "Lai", "Yamauchi"};

    public String firstName = factoryUtil.getRandomString(FIRST_NAME);
    public String lastName = factoryUtil.getRandomString(LAST_NAME);
    public String username = "";
    public String email = firstName.replace("'","").toLowerCase() + "." + lastName.replace("'","").toLowerCase() + "@test-mail.com";
    public String password = "";

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

//    public static void main(String[] args) {
//        System.out.println("Hi, my name is: " + getFirstName() + " " + getLastName());
//        System.out.println("And my email is: " + getEmail());
//    }
}
