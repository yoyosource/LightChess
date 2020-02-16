package lightchess.board.implemented;

import lightchess.board.Board;
import lightchess.board.Piece;
import lightchess.board.PieceColor;
import lightchess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> possibilities(Board board, int x, int y) {
        List<Position> positions = new ArrayList<>();
        if (board.isEmpty(x + 2, y + 1)) {
            positions.add(new Position(x + 2, y + 1));
        }
        if (board.isEmpty(x + 2, y - 1)) {
            positions.add(new Position(x + 2, y - 1));
        }
        if (board.isEmpty(x - 2, y + 1)) {
            positions.add(new Position(x - 2, y + 1));
        }
        if (board.isEmpty(x - 2, y - 1)) {
            positions.add(new Position(x - 2, y - 1));
        }

        if (board.isEmpty(x + 1, y + 2)) {
            positions.add(new Position(x + 1, y + 2));
        }
        if (board.isEmpty(x + 1, y - 2)) {
            positions.add(new Position(x + 1, y - 2));
        }
        if (board.isEmpty(x - 1, y + 2)) {
            positions.add(new Position(x - 1, y + 2));
        }
        if (board.isEmpty(x - 1, y - 2)) {
            positions.add(new Position(x - 1, y - 2));
        }
        return positions;
    }

    @Override
    public String toString() {
        return getColor().toString() + "N";
    }

}
