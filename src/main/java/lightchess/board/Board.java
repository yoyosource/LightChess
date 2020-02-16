package lightchess.board;

import lightchess.board.implemented.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private Piece[][] pieces = new Piece[8][8];

    private Position click = null;

    private String defaultBoard = "BR|BN|BB|BQ|BK|BB|BN|BR|BP|BP|BP|BP|BP|BP|BP|BP|||||||||||||||||||||||||||||||||WP|WP|WP|WP|WP|WP|WP|WP|WR|WN|WB|WQ|WK|WB|WN|WR|";
    //private String defaultBoard = "|BN||||WB||||WP|||||||||||WQ||||WK|||||||WR||||||||||WB||BQ|||||||||||||||||||||";

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
        List<String> str = new ArrayList<>();
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                str.add(st.toString());
                st = new StringBuilder();
            } else {
                st.append(s.charAt(i));
            }
        }
        String[] strings = str.toArray(new String[0]);
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
        try {
            new Position(x, y);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return pieces[y][x].getColor() == PieceColor.UNDEFINED;
    }

    public boolean isEnemy(int x, int y, PieceColor color) {
        try {
            new Position(x, y);
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (color == PieceColor.UNDEFINED) {
            return false;
        }
        if (isEmpty(x, y)) {
            return false;
        }
        return pieces[y][x].getColor() != color;
    }

    public List<Position> getColoredPieces(PieceColor color) {
        List<Position> positions = new ArrayList<>();
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
                if (pieces[y][x].getColor() == color) {
                    positions.add(new Position(x, y));
                }
            }
        }
        return positions;
    }

    private boolean move(Position from, Position to) {
        List<Position> positions = pieces[from.getY()][from.getX()].possibilities(this, from.getX(), from.getY());
        if (positions.contains(to)) {
            Piece p = pieces[from.getY()][from.getX()];
            pieces[from.getY()][from.getX()] = new Piece(PieceColor.UNDEFINED);
            pieces[to.getY()][to.getX()] = p;
            return true;
        }
        return false;
    }

    public synchronized void click(Position click) {
        if (click == null) {
            this.click = null;
            return;
        }
        if (this.click != null) {
            if (move(this.click.copy(), click.copy())) {
                this.click = null;
                return;
            }
        }
        if (pieces[click.getY()][click.getX()].getColor() == PieceColor.UNDEFINED) {
            this.click = null;
        }
        this.click = click;
    }

    public void render(Graphics2D g, int ix, int iy){
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
                //pieces[y][x].render(g, ((pieces.length - 1) * 125 + ix * 2) - (x * 125 + ix), ((pieces[x].length - 1) * 125 + iy * 2) - (y * 125 + iy));
                pieces[y][x].render(g, (x * 125 + ix), (y * 125 + iy));
            }
        }
        if (click != null) {
            Position position = this.click.copy();
            List<Position> positions = pieces[position.getY()][position.getX()].possibilities(this, position.getX(), position.getY());
            g.setColor(new Color(100, 200, 100, 150));
            for (Position p : positions) {
                if (!isEmpty(p.getX(), p.getY())) {
                    g.setColor(new Color(200, 100, 100, 150));
                }
                g.fillOval(p.getX() * 125 + ix + 50, p.getY() * 125 + iy + 50, 25, 25);
                if (!isEmpty(p.getX(), p.getY())) {
                    g.setColor(new Color(100, 200, 100, 150));
                }
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
