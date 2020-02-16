package lightchess.stages;

import lightchess.MouseInput;
import lightchess.render.Button;
import lightchess.render.Fonts;
import lightchess.render.Stage;

import java.awt.*;

public class MenuStage implements Stage {

    int ani = 255;
    boolean way = false;
    int currentSelection = 1;
    lightchess.render.Button[] options = new Button[4];

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,800,600);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 45), new Color(255,103,0 , ani), "Light Chess", 150);

        for (int i = 0; i < options.length; i++) {
            if(i == currentSelection)
                options[i].setSelected(true);
            else
                options[i].setSelected(false);
            options[i].render(g);
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
        boolean intesects = false;
        for (int i = 0; i < options.length; i++) {
            if(options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1,1))){
                currentSelection = i;
                intesects = true;
            }

        }
        if( !intesects){
            currentSelection = -1;
        }
        if(MouseInput.wasReleased(1)){
            System.out.println(currentSelection);
        }

    }

    @Override
    public void start() {
        options[0] = new lightchess.render.Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "Play", 250 + 0*80);
        options[1] = new lightchess.render.Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "Online", 250 + 1*80);
        options[2] = new lightchess.render.Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "Options", 250 + 2*80);
        options[3] = new lightchess.render.Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "Exit", 250 + 3*80);
    }

    @Override
    public void end() {

    }
}
