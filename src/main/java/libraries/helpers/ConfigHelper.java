package libraries.helpers;

/**
 * Created by makri on 21/06/2017.
 */
public class ConfigHelper extends helpers.ConfigHelper {

    //Test Configuration related to the special project

    public static String getStartingURL() {
        return getString("starting.url");
    }
}
