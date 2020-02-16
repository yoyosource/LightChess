package lightchess.render;

import java.awt.*;

public class BordRenderer {

    public static void renderBoard(Graphics2D g, int x, int y){
        g.setColor(Color.black);
        g.fillRect(x, y, 1000, 1000);
        g.setColor(Color.white);
        g.fillRect(x + 125*1, y , 125, 125);
        g.fillRect(x + 125*3, y , 125, 125);
        g.fillRect(x + 125*5, y , 125, 125);
        g.fillRect(x + 125*7, y , 125, 125);

        g.fillRect(x + 125*0, y + 125, 125, 125);
        g.fillRect(x + 125*2, y + 125, 125, 125);
        g.fillRect(x + 125*4, y + 125, 125, 125);
        g.fillRect(x + 125*6, y + 125, 125, 125);

        g.fillRect(x + 125*1, y + 125 * 2 , 125, 125);
        g.fillRect(x + 125*3, y + 125 * 2 , 125, 125);
        g.fillRect(x + 125*5, y + 125 * 2 , 125, 125);
        g.fillRect(x + 125*7, y + 125 * 2 , 125, 125);

        g.fillRect(x + 125*0, y + 125 * 3 , 125, 125);
        g.fillRect(x + 125*2, y + 125 * 3 , 125, 125);
        g.fillRect(x + 125*4, y + 125 * 3 , 125, 125);
        g.fillRect(x + 125*6, y + 125 * 3 , 125, 125);

        g.fillRect(x + 125*1, y + 125 * 4 , 125, 125);
        g.fillRect(x + 125*3, y + 125 * 4 , 125, 125);
        g.fillRect(x + 125*5, y + 125 * 4 , 125, 125);
        g.fillRect(x + 125*7, y + 125 * 4 , 125, 125);

        g.fillRect(x + 125*0, y + 125 * 5 , 125, 125);
        g.fillRect(x + 125*2, y + 125 * 5 , 125, 125);
        g.fillRect(x + 125*4, y + 125 * 5 , 125, 125);
        g.fillRect(x + 125*6, y + 125 * 5 , 125, 125);

        g.fillRect(x + 125*1, y + 125 * 6 , 125, 125);
        g.fillRect(x + 125*3, y + 125 * 6 , 125, 125);
        g.fillRect(x + 125*5, y + 125 * 6, 125, 125);
        g.fillRect(x + 125*7, y + 125 * 6 , 125, 125);

        g.fillRect(x + 125*0, y + 125 * 7 , 125, 125);
        g.fillRect(x + 125*2, y + 125 * 7 , 125, 125);
        g.fillRect(x + 125*4, y + 125 * 7 , 125, 125);
        g.fillRect(x + 125*6, y + 125 * 7, 125, 125);
    }
}
