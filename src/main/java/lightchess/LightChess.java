package lightchess;

import lightchess.config.ConfigManager;
import lightchess.render.Draw;
import lightchess.render.Stage;
import lightchess.resourceManager.LoadingQueue;
import lightchess.resourceManager.ResourceManager;
import lightchess.stages.*;
import lightchess.utils.OptionManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LightChess {

    public static Draw draw;
    public static List<Stage> stages = new ArrayList<>();
    public static long frames;

    public static ResourceManager resourceManager = new ResourceManager();

    public static void main(String[] args) {
        LoadingQueue loadingQueue = new LoadingQueue();
        loadingQueue.add("pieces/white/Pawn.png", "WP", false);
        loadingQueue.add("pieces/black/Pawn.png", "BP", false);

        loadingQueue.add("pieces/white/Bishop.png", "WB", true);
        loadingQueue.add("pieces/white/King.png", "WK", true);
        loadingQueue.add("pieces/white/Knight.png", "WN", true);
        loadingQueue.add("pieces/white/Queen.png", "WQ", true);
        loadingQueue.add("pieces/white/Rook.png", "WR", true);

        loadingQueue.add("pieces/black/Bishop.png", "BB", true);
        loadingQueue.add("pieces/black/King.png", "BK", true);
        loadingQueue.add("pieces/black/Knight.png", "BN", true);
        loadingQueue.add("pieces/black/Queen.png", "BQ", true);
        loadingQueue.add("pieces/black/Rook.png", "BR", true);
        resourceManager.load(loadingQueue);

        ConfigManager.createUUID();

        initStages();

        OptionManager.load();

        draw = new Draw();

        JFrame jFrame = new JFrame();
        jFrame.setTitle("LightChess");
        jFrame.setName("LightChess");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1400;
        int height = 1100;
        if (height > d.height) {
            Draw.scale = ((double)d.height - 30) / height;
            width = (int)(width * Draw.scale);
            height = (int)(height * Draw.scale);
        }
        if (width > d.width) {
            Draw.scale = (double)d.width / width;
            width = (int)(width * Draw.scale);
            height = (int)(height * Draw.scale);
        }
        jFrame.setSize(width, height);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
        jFrame.add(draw);
        jFrame.validate();

        MouseInput mi = new MouseInput();
        draw.addMouseListener(mi);
        draw.addMouseMotionListener(mi);
        draw.addKeyListener(new KeyInput());

        Runnable runnable = () -> {
            int i = 0;
            while (true) {
                draw.tick();
                MouseInput.update();
                KeyInput.update();
                i++;
                if(i > 60){
                    i = 0;
                    //System.out.println(frames);
                    frames = 0;
                }
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();;
                }
            }
        };
        Runnable runnable1 = () ->{
            while (true){
                draw.render();
                frames++;
                if(OptionManager.getoption("limitfps")){
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();;
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setName("Game-Tick");
        t.start();
        Thread t2 = new Thread(runnable1);
        t2.setName("Renderer");
        t2.start();
    }

    public static void initStages() {
        stages.add(new MenuStage());
        stages.add(new OptionsStage());
        stages.add(new OfflineStage());
        stages.add(new CreditStage());
        stages.add(new ConnectStage());
        stages.add(new OnlineStage());
    }

}
