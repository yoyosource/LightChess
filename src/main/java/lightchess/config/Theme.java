package lightchess.config;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

public class Theme {

    private String name;
    private File file;

    private int type = -1;

    public Theme(String name, File file) {
        this.name = name;
        this.file = file;

        if (file.isDirectory()) {
            File[] f = file.listFiles((dir, name1) -> name1.endsWith(".chesstheme"));
            if (f == null) {
                return;
            }
            if (f.length == 0) {
                return;
            }
            if (f.length == 1) {
                this.type = 0;
            }
        } else if (file.getName().endsWith(".zip")) {
            this.type = 1;
        }
    }

    public boolean valid() {
        return type != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Theme)) return false;
        Theme theme = (Theme) o;
        return Objects.equals(name, theme.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
