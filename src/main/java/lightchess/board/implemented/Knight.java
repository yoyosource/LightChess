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

        int i = -2;
        int j = -1;
        while (i <= 2) {
            while (j <= 1) {
                if (board.isEmpty(x + i, y + j)) {
                    positions.add(new Position(x + i, y + j));
                } else {
                    if (board.isEnemy(x + i, y + j, getColor())) {
                        positions.add(new Position(x + i, y + j));
                    }
                }
                if (board.isEmpty(x + j, y + i)) {
                    positions.add(new Position(x + j, y + i));
                } else {
                    if (board.isEnemy(x + j, y + i, getColor())) {
                        positions.add(new Position(x + j, y + i));
                    }
                }
                j += 2;
            }
            j = -1;
            i += 4;
        }

        return positions;
    }

    @Override
    public String toString() {
        return getColor().toString() + "N";
    }

}
