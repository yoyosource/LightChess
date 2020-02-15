package lightchess.stages;

import lightchess.render.Stage;

import java.awt.*;

public class MenuStage implements Stage {
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0,0,800,600);
        Font f = Font.getFont(Font.MONOSPACED);
        g.setFont(f);
        g.setColor(Color.orange);
        g.drawString("Test", 10,20);
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
