package lightchess.stages;

import lightchess.KeyInput;
import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.utils.OptionManager;
import lightchess.render.Button;
import lightchess.utils.CheckIntersection;
import lightchess.render.Fonts;
import lightchess.render.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsStage implements Stage {

    private Button[] buttons = new Button[2];
    private int currentSelection = -1;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,1400,1100);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 70), new Color(255,103,0), "Options", 100);
        for (int i = 0; i < buttons.length; i++) {
            if (i == currentSelection) {
                buttons[i].setSelected(true);
            } else {
                buttons[i].setSelected(false);
            }
            buttons[i].render(g);
        }
        g.drawOval(50, 50, 50 ,50);
        g.setColor(new Color(255,103,0));
        g.drawRect(275, 175, 25, 25);
        if(OptionManager.getoption("limitfps")){
            Fonts.string(g,new Font("Old English Text MT", Font.PLAIN, 40), new Color(255,103,0), "✓", 275, 195);
        }

    }

    @Override
    public void tick() {
        if(KeyInput.wasPressed(KeyEvent.VK_ESCAPE)){
            LightChess.draw.setStage(0);
        }
        boolean intersects = false;
        for (int i = 0; i < buttons.length; i++) {
            if (CheckIntersection.intersects(buttons[i], MouseInput.getX(), MouseInput.getY())) {
                currentSelection = i;
                intersects = true;
            }

        }
        if (!intersects) {
            currentSelection = -1;
        }
        if (MouseInput.wasReleased(1)) {
            if(currentSelection == 0){
                LightChess.draw.setStage(0);
            }
            if (currentSelection == 1) {
                OptionManager.setOption("limitfps", !OptionManager.getoption("limitfps"));
            }
        }
    }

    @Override
    public void start() {
        buttons[0] = new Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "←", 55, 87);
        buttons[1] = new Button(new Font("Old English Text MT", Font.PLAIN, 35),new Font("Old English Text MT", Font.PLAIN, 35),
                new Color(255,103,0, 155), new Color(255,103,0), "Limit FPS", 100, 200);
        buttons[1].setWidhtplus(50);
    }

    @Override
    public void end() {

    }
}
