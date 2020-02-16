package lightchess.board.implemented;

import lightchess.board.Board;
import lightchess.board.Piece;
import lightchess.board.PieceColor;
import lightchess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(PieceColor color) {
        super(color);
    }

    boolean b = false;

    @Override
    public List<Position> possibilities(Board board, int x, int y) {
        if (b) {
            return new ArrayList<>();
        }
        List<Position> pieces = board.getColoredPieces(PieceColor.getColor((getColor().getColor() + 1) % 2));
        List<Position> available = new ArrayList<>();
        b = true;
        for (Position p : pieces) {
            List<Position> ps = board.getPieces()[p.getY()][p.getX()].possibilities(board, p.getX(), p.getY());
            for (Position i : ps) {
                if (!available.contains(i)) {
                    available.add(i);
                }
            }
        }
        List<Position> positions = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (board.isEmpty(x + i, y + j)) {
                    positions.add(new Position(x + i, y + j));
                } else {
                    if (board.isEnemy(x + i, y + j, getColor())) {
                        positions.add(new Position(x + i, y + j));
                    }
                }
            }
        }
        positions.removeAll(available);

        b = false;
        return positions;
    }

    @Override
    public String toString() {
        return getColor().toString() + "K";
    }
}
