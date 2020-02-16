package lightchess.stages;

import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.OptionManager;
import lightchess.board.Board;
import lightchess.render.BordRenderer;
import lightchess.render.Button;
import lightchess.render.Stage;

import java.awt.*;

public class OfflineStage implements Stage {

    private lightchess.render.Button[] buttons = new Button[1];
    private int currentSelection = -1;
    private Board board;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);

        g.fillRect(0,0,1400,1100);
        BordRenderer.renderBoard(g, 350, 30);
        if (board != null) {
            board.render(g, 350, 30);
        }

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
            System.out.println(currentSelection);
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
        board = Board.createBoard();
    }

    @Override
    public void end() {

    }

}
