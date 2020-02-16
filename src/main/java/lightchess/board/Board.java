package lightchess.board;

import lightchess.board.implemented.*;

import java.awt.*;
import java.util.List;

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private boolean flip = false;

    private Position click = null;

    private String defaultBoard = "WR|WN|WB|WQ|WK|WB|WN|WR|WP|WP|WP|WP|WP|WP|WP|WP|||||||||||||||||||||||||||||||||BP|BP|BP|BP|BP|BP|BP|BP|BR|BN|BB|BQ|BK|BB|BN|BR";

    public static void main(String[] args) {
        createBoard();
    }

    private Board() {
        board(defaultBoard);
    }

    private Board(String s) {
        board(s);
    }

    private void board(String s) {
        String[] strings = defaultBoard.split("\\|");
        if (strings.length != 64) {
            throw new IllegalArgumentException("Board is not 64 tiles big. (" + strings.length + ")");
        }
        for (int i = 0; i < strings.length; i++) {
            int x = i % 8;
            int y = (i - x) / 8;
            pieces[y][x] = getPiece(strings[i]);
        }
        System.out.println(this);
    }

    private Piece getPiece(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return new Piece(PieceColor.UNDEFINED);
        }
        if (s.length() != 2) {
            return new Piece(PieceColor.UNDEFINED);
        }
        PieceColor color;
        if (s.charAt(0) == 'W') {
            color = PieceColor.WHITE;
        } else if (s.charAt(0) == 'B') {
            color = PieceColor.BLACK;
        } else {
            return null;
        }
        char c = s.charAt(1);
        if (c == 'R') {
            return new Rook(color);
        }
        if (c == 'N') {
            return new Knight(color);
        }
        if (c == 'B') {
            return new Bishop(color);
        }
        if (c == 'Q') {
            return new Queen(color);
        }
        if (c == 'K') {
            return new King(color);
        }
        if (c == 'P') {
            return new Pawn(color);
        }
        return new Piece(PieceColor.UNDEFINED);
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public static Board createBoard() {
        return new Board();
    }

    public static Board createBoard(String s) {
        return new Board(s);
    }

    public boolean isEmpty(int x, int y) {
        return pieces[y][x].getColor() == PieceColor.UNDEFINED;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    private boolean move(Position from, Position to) {
        from.flipY();
        List<Position> positions = pieces[from.getY()][from.getX()].possibilities(this, from.getX(), from.getY());
        to.flipX();
        System.out.println(from + "   " + to + " -> " +  positions);
        if (positions.contains(to)) {
            from.flip();
            Piece p = pieces[from.getY()][from.getX()];
            pieces[from.getY()][from.getX()] = new Piece(PieceColor.UNDEFINED);
            pieces[to.getY()][to.getX()] = p;
            return true;
        }
        return false;
    }

    public synchronized void click(Position click) {
        if (click == null) {
            return;
        }
        if (this.click != null) {
            if (move(this.click.copy(), click.copy())) {
                click = null;
                return;
            }
        }
        System.out.println(toString());
        if (pieces[click.getY()][click.getX()].getColor() == PieceColor.UNDEFINED) {
            this.click = null;
        }
        this.click = click;
    }

    public void render(Graphics2D g, int ix, int iy){
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
                if (!flip) {
                    pieces[y][x].render(g, x * 125 + ix, ((pieces[x].length - 1) * 125 + iy * 2) - (y * 125 + iy));
                } else {
                    pieces[y][x].render(g, x * 125 + ix, y * 125 + iy);
                }
            }
        }
        if (click != null) {
            Position position = this.click.copy();
            position.flipY();
            List<Position> positions = pieces[position.getY()][position.getX()].possibilities(this, position.getX(), position.getY());
            g.setColor(new Color(100, 200, 100, 100));
            for (Position p : positions) {
                p.flipY();
                g.fillRect(p.getX() * 125 + ix, p.getY() * 125 + iy, 125, 125);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
                if (!(x == 0 && y == 0)) {
                    st.append("|");
                }
                if (pieces[x][y] == null) {
                    st.append("  ");
                } else {
                    st.append(pieces[x][y].toString());
                }
            }
        }
        return st.toString().trim();
    }

}
