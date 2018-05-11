package libraries.helpers;

/**
 * Created by makri on 21/06/2017.
 */
public class ConfigHelper extends helpers.ConfigHelper {

    //Test Configuration related to the special project

    public static String getStartingURL() {
        return getString("starting.url");
    }

    public static String getDefaultUserName() {
        return  getString("default.username");
    }

    public static String getDefaultPass() {
        return  getString("default.password");
    }

    public static int getTargetNumber() {
        return getInt("target.number");
    }
}
