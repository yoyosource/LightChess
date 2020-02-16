package lightchess.render;

import java.awt.*;

public class Button extends Rectangle {

    private Font font, selectedfont;
    private Color color, selectedcolor;
    private boolean selected;
    private String text;
    private int texty;

    public Button(Font font, Font selectedfont, Color color, Color selectedcolor, String text, int y) {
        this.font = font;
        this.selectedfont = selectedfont;
        this.color = color;
        this.selectedcolor = selectedcolor;
        this.selected = false;
        this.text = text;
        this.texty = y;
    }

    public void setSelected(boolean bool){
        this.selected = bool;
    }

    public void render(Graphics2D g){
        if(selected)
            Fonts.string(g, selectedfont, selectedcolor, text, texty);
        else
            Fonts.string(g, font, color, text, texty);
        FontMetrics fm = g.getFontMetrics();
        this.x = 500 - fm.stringWidth(text) / 2;
        this.y = texty - fm.getHeight();
        this.width = fm.stringWidth(text);
        this.height = fm.getHeight();
    }
}
