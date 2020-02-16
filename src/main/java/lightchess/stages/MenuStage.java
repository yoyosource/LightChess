package lightchess.stages;

import lightchess.MouseInput;
import lightchess.render.Fonts;
import lightchess.render.Stage;

import java.awt.*;

public class MenuStage implements Stage {

    int ani = 255;
    boolean way = false;
    String[] options = {"Play", "Options", "Exit"};
    int selected = 1;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,800,600);
        Font f = new Font("Old English Text MT", Font.PLAIN, 45);
        g.setColor(new Color(255,103,0 , ani));
        g.setFont(f);
        g.setFont(new Font("Old English Text MT", Font.PLAIN, 45));
        FontMetrics fm1 = g.getFontMetrics(f);
        String str1 = "Light Chess";
        int x = 400 - (fm1.stringWidth(str1) / 2);
        g.drawString(str1, x , 150);
        for (String str:
                options) {
            FontMetrics fm = g.getFontMetrics(f);
            if(MouseInput.x >= 400 - fm.stringWidth(str) / 2){
                if(MouseInput.x <= 400 + fm.stringWidth(str) / 2){
                    if(MouseInput.y >= 0) {
                        return;
                    }
                }
            }
        }
        Color c = new Color(255,103,0);
        Color c1 = new Color(255,103,0, 155);
        for (int i = 0; i < options.length; i++) {
            if(i == selected){
                Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 55), c, options[i], 250 + i*80);
            }else{
                Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 45), c1, options[i], 250 + i*80);
            }
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

    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
