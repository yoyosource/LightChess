package lightchess.stages;

import lightchess.render.Stage;

import java.awt.*;

public class MenuStage implements Stage {
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,800,600);
        Font f = new Font("Old English Text MT", Font.PLAIN, 45);
        g.setColor(Color.orange);
        g.setFont(f);
        g.setFont(new Font("Old English Text MT", Font.PLAIN, 45));
        FontMetrics fm = g.getFontMetrics(f);
        String str = "Light Chess";
        int x = 400 - (fm.stringWidth(str) / 2);
        g.drawString(str, x , 150);
    }

    @Override
    public void tick() {

    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
