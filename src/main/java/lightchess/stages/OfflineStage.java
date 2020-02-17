package lightchess.stages;

import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.board.Board;
import lightchess.board.Position;
import lightchess.render.*;
import lightchess.render.Button;
import lightchess.utils.CheckIntersection;

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
        g.setColor(Color.GRAY);

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
            if (CheckIntersection.intersects(buttons[i], MouseInput.getX(), MouseInput.getY())) {
                currentSelection = i;
                intersects = true;
            }

        }
        if (!intersects) {
            currentSelection = -1;
        }
        if (MouseInput.wasReleased(1)) {
            if(currentSelection == 0){
                LightChess.draw.setStage(0);
            }
        }

        if (MouseInput.wasReleased(1)) {
            int scaled350 = (int)(350 * Draw.scale);
            int scaled125 = (int)(125 * Draw.scale);
            int scaled30 = (int)(30 * Draw.scale);
            int x = MouseInput.getX() - scaled350;
            int y = MouseInput.getY() - scaled30;
            if (MouseInput.getX() < scaled350) {
                board.click(null);
                return;
            }
            if (MouseInput.getY() < scaled30) {
                board.click(null);
                return;
            }
            x = x / scaled125;
            y = y / scaled125;
            try {
                Position position = new Position(x, y);
                board.click(position);
            } catch (IllegalArgumentException e) {

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
