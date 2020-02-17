package lightchess.render;

import lightchess.LightChess;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Draw extends Canvas {

    public static double scale = 0.8;

    private Stage currstage;

    public Draw(){
        currstage = LightChess.stages.get(0);
        currstage.start();
    }

    public void setStage(Integer i){
        currstage.end();
        currstage = LightChess.stages.get(i);
        currstage.start();
    }

    public void tick() {
        currstage.tick();
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.scale(scale, scale);

        currstage.render(g);

        g.dispose();
        bs.show();
    }


}
