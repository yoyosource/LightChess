package lightchess.render;

import java.awt.*;

public class Button extends Rectangle {

    private Font font, selectedfont;
    private Color color, selectedcolor;
    private boolean selected;
    private String text;
    private int texty, textx;
    private int widhtplus;

    public Button(Font font, Font selectedfont, Color color, Color selectedcolor, String text, int y) {
        this.font = font;
        this.selectedfont = selectedfont;
        this.color = color;
        this.selectedcolor = selectedcolor;
        this.selected = false;
        this.text = text;
        this.texty = y;
        this.textx = -1;
        widhtplus = 0;
    }
    public Button(Font font, Font selectedfont, Color color, Color selectedcolor, String text, int x, int y) {
        this.font = font;
        this.selectedfont = selectedfont;
        this.color = color;
        this.selectedcolor = selectedcolor;
        this.selected = false;
        this.text = text;
        this.texty = y;
        this.textx = x;
        widhtplus = 0;
    }

    public void setSelected(boolean bool){
        this.selected = bool;
    }

    public void setWidhtplus(int i){
        widhtplus = i;
    }

    public void render(Graphics2D g){
        if(textx == -1){
            if(selected)
                Fonts.string(g, selectedfont, selectedcolor, text, texty);
            else
                Fonts.string(g, font, color, text, texty);
            FontMetrics fm = g.getFontMetrics();
            this.x = 700 - fm.stringWidth(text) / 2;
            this.y = texty - fm.getHeight();
            this.width = fm.stringWidth(text) + widhtplus;
            this.height = fm.getHeight();
        }else{
            if(selected)
                Fonts.string(g, selectedfont, selectedcolor, text, textx, texty);
            else
                Fonts.string(g, font, color, text, textx, texty);
            FontMetrics fm = g.getFontMetrics();
            this.x = textx;
            this.y = texty - fm.getHeight();
            this.width = fm.stringWidth(text) + widhtplus;
            this.height = fm.getHeight();
        }

    }
}
