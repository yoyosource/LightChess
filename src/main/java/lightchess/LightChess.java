package lightchess;

import lightchess.render.Draw;
import lightchess.render.Stage;
import lightchess.stages.MenuStage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LightChess {

    private static Draw draw;
    public static List<Stage> stages = new ArrayList<>();

    public static void main(String[] args) {

        initStages();

        draw = new Draw();

        JFrame jFrame = new JFrame();
        jFrame.setTitle("LightChess");
        jFrame.setName("LightChess");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setSize(1000, 700);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
        jFrame.add(draw);
        jFrame.validate();

        MouseInput mi = new MouseInput();
        draw.addMouseListener(mi);
        draw.addMouseMotionListener(mi);

        Runnable runnable = () -> {
            while (true) {
                draw.tick();
                draw.render();
                MouseInput.update();
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
        stages.add(new MenuStage());
    }

}
