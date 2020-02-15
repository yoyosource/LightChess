package lightchess;

import lightchess.render.Draw;
import lightchess.render.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LightChess {

    private static Draw draw = new Draw();
    public static List<Stage> stages = new ArrayList<>();

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("LightChess");
        jFrame.setName("LightChess");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
        jFrame.add(draw);
        jFrame.validate();

        Runnable runnable = () -> {
            while (true) {
                draw.tick();
                draw.render();
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();;
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setName("Renderer");
        t.start();
    }

    public static void initStages() {

    }

}
