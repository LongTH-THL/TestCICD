package cucumber.singleton.user;

import cucumber.models.User;
import cucumber.singleton.Utilities;

public class PodFood {

    //password 12345678a
    private static final String PASSWORD = Utilities.PasswordUnmasking("MTIzNDU2Nzhh");

    public static User thuyExam() {
        return new User("thuy+exam1@podfoods.co", PASSWORD);
    }

    public static User admin() {
        return new User("admin@beta.podfoods.co", "Abc@12345678");
    }
}

