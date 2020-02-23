package lightchess.stages;

import lightchess.render.BordRenderer;
import lightchess.render.Stage;

import java.awt.*;

public class OnlineStage implements Stage {
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);

        g.fillRect(0,0,1400,1100);
        BordRenderer.renderBoard(g, 350, 30);
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
