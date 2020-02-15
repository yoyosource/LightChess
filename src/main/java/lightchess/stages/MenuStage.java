package lightchess.stages;

import lightchess.render.Stage;

import java.awt.*;

public class MenuStage implements Stage {

    int ani = 255;
    boolean way = false;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,800,600);
        Font f = new Font("Old English Text MT", Font.PLAIN, 45);
        g.setColor(new Color(255,103,0 , ani));
        g.setFont(f);
        g.setFont(new Font("Old English Text MT", Font.PLAIN, 45));
        FontMetrics fm = g.getFontMetrics(f);
        String str = "Light Chess";
        int x = 400 - (fm.stringWidth(str) / 2);
        g.drawString(str, x , 150);
    }

    @Override
    public void tick() {
        if (way) {
            ani += 3;
            if (ani > 255) {
                ani = 255;
                way = false;
            }
        } else {
            ani -= 1;
            if (ani < 100) {
                ani = 100;
                way = true;
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
