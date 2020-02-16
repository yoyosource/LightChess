package lightchess.board;

import lightchess.LightChess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Piece {

    private PieceColor color;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public final PieceColor getColor() {
        return color;
    }

    public List<Position> possibilities(Board board, int x, int y) {
        return new ArrayList<>();
    }

    public void render(Graphics2D g, int x, int y) {
        g.drawImage(LightChess.resourceManager.getImage(this.toString()), x, y, null);
    }

    @Override
    public String toString() {
        return getColor().toString() + " ";
    }

}
