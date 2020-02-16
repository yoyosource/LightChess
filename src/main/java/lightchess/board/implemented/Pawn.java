package lightchess.board.implemented;

import lightchess.board.Board;
import lightchess.board.Piece;
import lightchess.board.PieceColor;
import lightchess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> possibilities(Board board, int x, int y) {
        List<Position> positions = new ArrayList<>();
        if (getColor() == PieceColor.WHITE) {
            if (y == 6 && board.isEmpty(x , y - 2)) {
                positions.add(new Position(x, y - 2));
            }
            if (board.isEmpty(x, y - 1)) {
                positions.add(new Position(x, y - 1));
            }
            if (board.isEnemy(x - 1, y - 1, getColor())) {
                positions.add(new Position(x - 1, y - 1));
            }
            if (board.isEnemy(x + 1, y - 1, getColor())) {
                positions.add(new Position(x + 1, y - 1));
            }
        }
        if (getColor() == PieceColor.BLACK) {
            if (y == 1 && board.isEmpty(x , y + 2)) {
                positions.add(new Position(x, y + 2));
            }
            if (board.isEmpty(x, y + 1)) {
                positions.add(new Position(x, y + 1));
            }
            if (board.isEnemy(x + 1, y + 1, getColor())) {
                positions.add(new Position(x + 1, y + 1));
            }
            if (board.isEnemy(x - 1, y + 1, getColor())) {
                positions.add(new Position(x - 1, y + 1));
            }
        }
        return positions;
    }

    @Override
    public String toString() {
        return getColor().toString() + "P";
    }

}
