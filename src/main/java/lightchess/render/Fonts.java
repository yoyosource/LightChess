package lightchess.render;

import java.awt.*;

public class Fonts {

    public static void string(Graphics2D g, Font f, Color c, String str, int x, int y){
        g.setColor(c);
        g.setFont(f);
        g.drawString(str, x, y);
    }
    public static void string(Graphics2D g, Font f, Color c, String str, int y){
        g.setColor(c);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics(f);
        int x = 700 - (fm.stringWidth(str) / 2);
        g.drawString(str, x, y);
    }
}
