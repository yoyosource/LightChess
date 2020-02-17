package lightchess.utils;

import lightchess.render.Draw;

import java.awt.*;

public class CheckIntersection {

    public static Graphics2D g = null;

    public static boolean intersects(Rectangle r, int x, int y) {
        return getScaledRectangle(r).intersects(createRectangle(x, y, 1, 1));
    }

    public static Rectangle getScaledRectangleLocation(Rectangle r) {
        Rectangle rs = r.getBounds();
        rs.setLocation((int)(r.x * Draw.scale), (int)(r.y * Draw.scale));
        return rs;
    }

    public static Rectangle getScaledRectangleSize(Rectangle r) {
        Rectangle rs = r.getBounds();
        rs.setSize((int)(r.width * Draw.scale), (int)(r.height * Draw.scale));
        return rs;
    }

    public static Rectangle getScaledRectangle(Rectangle r) {
        Rectangle rs = r.getBounds();
        rs = getScaledRectangleLocation(rs);
        return getScaledRectangleSize(rs);
    }

    public static Rectangle createScaledRectangle(int x, int y, int width, int height) {
        if ((int)(width * Draw.scale) != 0) {
            width = (int)(width * Draw.scale);
        }
        if ((int)(height * Draw.scale) != 0) {
            height = (int)(height * Draw.scale);
        }
        return new Rectangle((int)(x * Draw.scale), (int)(y * Draw.scale), width, height);
    }

    public static Rectangle createRectangle(int x, int y, int width, int height) {
        return new Rectangle(x, y, width, height);
    }

    public static void renderRectangle(Graphics2D g, Rectangle rectangle) {
        Color c = g.getColor();
        g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(c.getAlpha() * 0.8)));
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(c);
    }

}
