package lightchess.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ConfigManager {

    private static String getConfigDir() {
        return System.getProperty("user.home") + "/lightchess";
    }
    private static String getDesignDir() {
        return getConfigDir() + "/designs";
    }
    private static String getGamesDir() {
        return getConfigDir() + "/games";
    }

    private static String getConfig() {
        return getConfigDir() + "/.lightchess.config";
    }

    public static void createUUID() {
        File dir = new File(getConfigDir());
        if (!dir.exists()) {
            dir.mkdir();
        }
        File design = new File(getDesignDir());
        if (!design.exists()) {
            design.mkdir();
        }
        File games = new File(getGamesDir());
        if (!games.exists()) {
            games.mkdir();
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

    public static List<Theme> getThemes() {
        File design = new File(getDesignDir());
        if (!design.exists()) {
            design.mkdir();
        }

        File[] files = design.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }
        if (files.length == 0) {
            return new ArrayList<>();
        }

        List<Theme> themes = new ArrayList<>();
        for (File f : files) {
            Theme t = new Theme(f.getName(), f);
            if (!t.valid()) {
                continue;
            }
            themes.add(t);
        }
        return themes;
    }

}
