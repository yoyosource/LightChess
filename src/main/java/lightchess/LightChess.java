package lightchess;

import lightchess.render.Draw;

import javax.swing.*;

public class LightChess {

    private static Draw draw = new Draw();

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("LightChess");
        jFrame.setName("LightChess");
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
        jFrame.setContentPane(draw);
        jFrame.validate();

        Runnable runnable = () -> {
            while (true) {
                jFrame.repaint();
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

}
