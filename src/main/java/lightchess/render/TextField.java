package lightchess.render;

import java.awt.*;

public class TextField extends Rectangle {

    private Font font;
    private Color color, textcolor;
    private String text;
    private int boxx, boxy;
    private int widht, height1;
    private boolean selected;

    public TextField(Font font, Color color, Color textcolor, String text, int boxx, int boxy, int widht, int height) {
        this.font = font;
        this.color = color;
        this.textcolor = textcolor;
        this.text = text;
        this.boxx = boxx;
        this.boxy = boxy;
        this.widht = widht;
        this.height1 = height;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(Graphics2D g){
        g.setColor(color);
        g.fillRect(boxx, boxy, widht, height1);
        FontMetrics fm = g.getFontMetrics(font);
        Fonts.string(g, font, textcolor, text, boxx + 30, boxy + fm.getHeight());
        if(selected){
            g.setColor(Color.BLUE);
            g.fillRect(boxx, boxy, 5, height1);
            g.fillRect(boxx, boxy, widht, 5);
            g.fillRect(boxx, boxy + height1 - 5, widht, 5);
            g.fillRect(boxx + widht - 5, boxy, 5, height1);
        }
        this.x = boxx;
        this.y = boxy;
        this.width = widht;
        this.height = height1;
    }
}
