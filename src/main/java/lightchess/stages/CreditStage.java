package lightchess.stages;

import lightchess.KeyInput;
import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.OptionManager;
import lightchess.render.BordRenderer;
import lightchess.render.Button;
import lightchess.render.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CreditStage implements Stage {

    private lightchess.render.Button[] buttons = new Button[1];
    private int currentSelection = -1;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);

        g.fillRect(0,0,1400,1100);
        for (int i = 0; i < buttons.length; i++) {
            if (i == currentSelection) {
                buttons[i].setSelected(true);
            } else {
                buttons[i].setSelected(false);
            }
            buttons[i].render(g);
        }
        g.drawOval(50, 50, 50 ,50);

    }

    @Override
    public void tick() {
        if(KeyInput.wasPressed(KeyEvent.VK_ESCAPE)){
            LightChess.draw.setStage(0);
        }
        boolean intersects = false;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1,1))) {
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
            if(currentSelection == 1){
                OptionManager.setOption("limitfps", !OptionManager.getoption("limitfps"));
            }
        }
    }

    @Override
    public void start() {
        buttons[0] = new Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "←", 55, 87);
    }

    @Override
    public void end() {

    }
}
