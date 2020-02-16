package lightchess.stages;

import lightchess.KeyInput;
import lightchess.LightChess;
import lightchess.render.Fonts;
import lightchess.render.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsStage implements Stage {
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,1000,700);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 70), new Color(255,103,0), "Options", 100);
    }

    @Override
    public void tick() {
        if(KeyInput.wasPressed(KeyEvent.VK_ESCAPE)){
            LightChess.draw.setStage(0);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
