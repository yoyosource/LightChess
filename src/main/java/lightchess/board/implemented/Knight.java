package lightchess.board.implemented;

import lightchess.board.Board;
import lightchess.board.Piece;
import lightchess.board.PieceColor;
import lightchess.board.Position;

import java.awt.*;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> possibilities(Board board, int x, int y) {
        return super.possibilities(board, x, y);
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {

    }

    @Override
    public String toString() {
        return getColor().toString() + "N";
    }

}
