package lightchess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ConfigManager {

    private static String getConfigDir() {
        return System.getProperty("user.home") + "/.lightchess";
    }

    private static String getConfig() {
        return getConfigDir() + "/lightchess.config";
    }

    public static void createUUID() {
        File dir = new File(getConfigDir());
        if (!dir.exists()) {
            dir.mkdir();
        }
        File config = new File(getConfig());
        boolean justCreated = false;
        if (!config.exists()) {
            try {
                config.createNewFile();
                justCreated = true;
            } catch (IOException e) {

            }
        }
        if (config.exists() && justCreated) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(config)) {
                UUID uuid = UUID.randomUUID();
                fileOutputStream.write(("uuid: " + uuid.toString()).getBytes());
            } catch (IOException e) {

            }
        }
    }

}
