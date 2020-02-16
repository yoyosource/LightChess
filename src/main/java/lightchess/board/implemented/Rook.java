package lightchess.board.implemented;

import lightchess.board.Board;
import lightchess.board.Piece;
import lightchess.board.PieceColor;
import lightchess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> possibilities(Board board, int x, int y) {
        List<Position> positions = new ArrayList<>();
        int i = 1;
        while (i < 8) {
            if (board.isEmpty(x + i, y)) {
                positions.add(new Position(x + i, y));
            } else {
                if (board.isEnemy(x + i, y, getColor())) {
                    positions.add(new Position(x + i, y));
                }
                break;
            }
            i++;
        }
        i = 1;
        while (i < 8) {
            if (board.isEmpty(x - i, y)) {
                positions.add(new Position(x - i, y));
            } else {
                if (board.isEnemy(x - i, y, getColor())) {
                    positions.add(new Position(x - i, y));
                }
                break;
            }
            i++;
        }
        i = 1;
        while (i < 8) {
            if (board.isEmpty(x, y + i)) {
                positions.add(new Position(x, y + i));
            } else {
                if (board.isEnemy(x, y + i, getColor())) {
                    positions.add(new Position(x, y + i));
                }
                break;
            }
            i++;
        }
        i = 1;
        while (i < 8) {
            if (board.isEmpty(x, y - i)) {
                positions.add(new Position(x, y - i));
            } else {
                if (board.isEnemy(x, y - i, getColor())) {
                    positions.add(new Position(x, y - i));
                }
                break;
            }
            i++;
        }

        return positions;
    }

    @Override
    public String toString() {
        return getColor().toString() + "R";
    }

}
