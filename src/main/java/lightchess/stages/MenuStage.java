package lightchess.stages;

import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.render.Button;
import lightchess.render.Fonts;
import lightchess.render.Stage;

import java.awt.*;

public class MenuStage implements Stage {

    int ani = 255;
    boolean way = false;
    int currentSelection = 1;
    lightchess.render.Button[] options = new Button[5];

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,1400,1100);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 100), new Color(255,103,0 , ani), "Light Chess", 150);

        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                options[i].setSelected(true);
            } else {
                options[i].setSelected(false);
            }
            options[i].render(g);
        }
        if(!LightChess.resourceManager.isLoading("WP")){
            g.drawImage(LightChess.resourceManager.getImage("WP"), 100, 100, null);
        }

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
        boolean intersects = false;
        for (int i = 0; i < options.length; i++) {
            if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1,1))) {
                currentSelection = i;
                intersects = true;
            }

        }
        if (!intersects) {
            currentSelection = -1;
        }
        if (MouseInput.wasReleased(1)) {
            System.out.println(currentSelection);
            if(currentSelection == 4){
                System.exit(0);
            }else if( currentSelection == 2){
                LightChess.draw.setStage(1);
            }else if( currentSelection == 0){
                LightChess.draw.setStage(2);
            }

        }

    }

    @Override
    public void start() {
        options[0] = new Button(new Font("Old English Text MT", Font.PLAIN, 75),new Font("Old English Text MT", Font.PLAIN, 85),
                new Color(255,103,0, 155), new Color(255,103,0), "Play", 300 + 0*150);
        options[1] = new Button(new Font("Old English Text MT", Font.PLAIN, 75),new Font("Old English Text MT", Font.PLAIN, 85),
                new Color(255,103,0, 155), new Color(255,103,0), "Online", 300 + 1*150);
        options[2] = new Button(new Font("Old English Text MT", Font.PLAIN, 75),new Font("Old English Text MT", Font.PLAIN, 85),
                new Color(255,103,0, 155), new Color(255,103,0), "Options", 300 + 2*150);
        options[3] = new Button(new Font("Old English Text MT", Font.PLAIN, 75),new Font("Old English Text MT", Font.PLAIN, 85),
                new Color(255,103,0, 155), new Color(255,103,0), "Credits", 300 + 3*150);
        options[4] = new Button(new Font("Old English Text MT", Font.PLAIN, 75),new Font("Old English Text MT", Font.PLAIN, 85),
                new Color(255,103,0, 155), new Color(255,103,0), "Exit", 300 + 4*150);
    }

    @Override
    public void end() {

    }
}
