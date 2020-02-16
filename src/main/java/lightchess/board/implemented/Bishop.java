package lightchess.board.implemented;

import lightchess.board.Board;
import lightchess.board.Piece;
import lightchess.board.PieceColor;
import lightchess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> possibilities(Board board, int x, int y) {
        List<Position> positions = new ArrayList<>();
        int i = 1;
        while (i < 8) {
            if (board.isEmpty(x + i, y + i)) {
                positions.add(new Position(x + i, y + i));
            } else {
                if (board.isEnemy(x + i, y + i, getColor())) {
                    positions.add(new Position(x + i, y + i));
                }
                break;
            }
            i++;
        }
        i = 1;
        while (i < 8) {
            if (board.isEmpty(x - i, y - i)) {
                positions.add(new Position(x - i, y - i));
            } else {
                if (board.isEnemy(x - i, y - i, getColor())) {
                    positions.add(new Position(x - i, y - i));
                }
                break;
            }
            i++;
        }
        i = 1;
        while (i < 8) {
            if (board.isEmpty(x - i, y + i)) {
                positions.add(new Position(x - i, y + i));
            } else {
                if (board.isEnemy(x - i, y + i, getColor())) {
                    positions.add(new Position(x - i, y + i));
                }
                break;
            }
            i++;
        }
        i = 1;
        while (i < 8) {
            if (board.isEmpty(x + i, y - i)) {
                positions.add(new Position(x + i, y - i));
            } else {
                if (board.isEnemy(x + i, y - i, getColor())) {
                    positions.add(new Position(x + i, y - i));
                }
                break;
            }
            i++;
        }
        return positions;
    }

    @Override
    public String toString() {
        return getColor().toString() + "B";
    }

}
