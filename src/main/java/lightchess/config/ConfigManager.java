package lightchess.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ConfigManager {

    private static String getConfigDir() {
        return System.getProperty("user.home") + "/lightchess";
    }
    private static String getDesignDir() {
        return getConfigDir() + "/themes";
    }
    private static String getGamesDir() {
        return getConfigDir() + "/games";
    }
    private static String getTempDir() {
        return getConfigDir() + "/.cache";
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
        File temp = new File(getTempDir());
        if (!temp.exists()) {
            temp.mkdir();
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

        List<Theme> themes = getThemes();
        System.out.println(themes);
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
            File t;
            if (f.getName().endsWith(".zip")) {
                t = unzipFile(f);
            } else {
                t = f;
            }
            Theme theme = new Theme(t.getName(), t);
            if (theme.valid() && !themes.contains(theme)) {
                themes.add(theme);
            }
        }
        return themes;
    }

    private static File unzipFile(File f) {
        String name = f.getName();
        File uf = new File(getTempDir() + "/" + name.substring(0, name.lastIndexOf('.')));

        if (f.lastModified() > uf.lastModified()) {

        }
        deleteDirectory(uf);
        unzip(f.getPath(), getTempDir());
        return uf;
    }

    private static void deleteDirectory(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            return;
        }
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        if (files.length == 0) {
            file.delete();
            return;
        }

        List<File> fileList = new ArrayList<>();
        fileList.addAll(Arrays.stream(files).collect(Collectors.toList()));
        while (!fileList.isEmpty()) {
            File f = fileList.remove(0);
            if (!f.exists()) {
                continue;
            }
            if (f.isFile() && !f.delete()) {
                fileList.add(f);
            } else {
                File[] dir = f.listFiles();
                if (dir == null) {
                    continue;
                }
                if (dir.length == 0) {
                    f.delete();
                    continue;
                }
                fileList.addAll(Arrays.stream(dir).collect(Collectors.toList()));
            }
        }
    }

    private static void unzip(String zipFilePath, String destDir) {
        if (!new File(destDir).exists()) new File(destDir).mkdirs();
        byte[] buffer = new byte[1024];
        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(new File(zipFilePath)))) {
            ZipEntry zipEntry = null;
            do {
                zipEntry = inputStream.getNextEntry();
                if (zipEntry == null) {
                    continue;
                }

                String fileName = zipEntry.getName();
                File newFile = new File(destDir + File.separator + fileName);
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = inputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    inputStream.closeEntry();
                }
            } while (zipEntry != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
