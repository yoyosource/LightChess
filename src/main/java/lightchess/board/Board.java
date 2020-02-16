package lightchess.board;

import lightchess.board.implemented.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private Piece[][] pieces = new Piece[8][8];

    private PieceColor turn = PieceColor.WHITE;
    private PieceColor yourColor = PieceColor.WHITE;

    private Position click = null;

    private String defaultBoard = "BR|BN|BB|BQ|BK|BB|BN|BR|BP|BP|BP|BP|BP|BP|BP|BP|||||||||||||||||||||||||||||||||WP|WP|WP|WP|WP|WP|WP|WP|WR|WN|WB|WQ|WK|WB|WN|WR|";

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

    public Position getKing(PieceColor color) {
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
                if (pieces[y][x].isKing() && pieces[y][x].getColor() == color) {
                    return new Position(x, y);
                }
            }
        }
        return null;
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
        if (turn != yourColor) {
            this.click = null;
            return;
        }
        if (this.click != null) {
            if (move(this.click.copy(), click.copy())) {
                this.click = null;
                turn = PieceColor.getColor((turn.getColor() + 1) % 2);
                yourColor = PieceColor.getColor(turn.getColor());
                return;
            }
        }
        if (pieces[click.getY()][click.getX()].getColor() != turn) {
            this.click = null;
            return;
        }
        this.click = click;
    }

    public void render(Graphics2D g, int ix, int iy){
        for (int x = 0; x < pieces.length; x++) {
            for (int y = 0; y < pieces[x].length; y++) {
                pieces[y][x].render(g, (x * 125 + ix), (y * 125 + iy));
            }
        }
        Color green = new Color(100, 200, 100, 150);
        g.setColor(green);
        if (turn == PieceColor.WHITE) {
            g.fillRect(350, 1000 + iy, 1000, 10);
        } else if (turn == PieceColor.BLACK) {
            g.fillRect(350, iy - 10, 1000, 10);
        }
        if (click != null) {
            Position position = this.click.copy();
            List<Position> positions = pieces[position.getY()][position.getX()].possibilities(this, position.getX(), position.getY());
            Color red = new Color(200, 100, 100, 150);

            for (Position p : positions) {
                if (!isEmpty(p.getX(), p.getY())) {
                    g.setColor(red);
                } else {
                    g.setColor(green);
                }
                g.fillOval(p.getX() * 125 + ix + 50, p.getY() * 125 + iy + 50, 25, 25);
            }
        }

        checkCheck(g, ix, iy, turn);
        checkCheck(g, ix, iy, PieceColor.getColor((turn.getColor() + 1) % 2));

        positionCalculator(g, ix, iy);
    }

    private void positionCalculator(Graphics2D g, int ix, int iy) {
        int whiteMaterial = calcMaterial(PieceColor.WHITE);
        int blackMaterial = calcMaterial(PieceColor.BLACK);

        g.setColor(new Color(75, 75, 75));
        g.fillRect(350 - 30, iy, 25, 1000);

        double factor = 12.8205128205;

        g.setColor(Color.BLACK);
        g.fillRect(350 - 28, iy + 2, 21, (int)(blackMaterial * factor));
        g.setColor(Color.WHITE);
        g.fillRect(350 - 28, 1000 + iy - 2 - (int)(whiteMaterial * factor), 21, (int)(whiteMaterial * factor));

        g.setColor(new Color(100, 200, 100));
        g.fillRect(350 - 28, 499 + iy, 21, 2);
    }

    private boolean checkCheck(Graphics2D g, int ix, int iy, PieceColor color) {
        Position king = getKing(color);
        if (king == null) {
            turn = PieceColor.UNDEFINED;
            return false;
        }
        List<Position> available = pieces[king.getY()][king.getX()].possibilities(this, king.getX(), king.getY());
        List<Position> attacked = attacked(color);

        if (available.isEmpty() && attacked.contains(king)) {
            //System.out.println("Checkmate?");
        }
        if (attacked.contains(king)) {
            g.setColor(new Color(200, 100, 100, 150));
            g.fillRect(king.getX() * 125 + ix, king.getY() * 125 + iy, 125, 125);
        }
        return true;
    }

    private List<Position> attacked(PieceColor color) {
        List<Position> coloredPieces = getColoredPieces(PieceColor.getColor((color.getColor() + 1) % 2));
        List<Position> positions = new ArrayList<>();
        for (Position p : coloredPieces) {
            positions.addAll(pieces[p.getY()][p.getX()].possibilities(this, p.getX(), p.getY()));
        }
        return positions;
    }

    private int calcMaterial(PieceColor color) {
        List<Position> coloredPieces = getColoredPieces(color);
        int value = 0;
        for (Position p : coloredPieces) {
            char c = pieces[p.getY()][p.getX()].toString().charAt(1);
            if (c == 'P') {
                value++;
            }
            if (c == 'B') {
                value += 3;
            }
            if (c == 'N') {
                value += 3;
            }
            if (c == 'R') {
                value += 5;
            }
            if (c == 'Q') {
                value += 9;
            }
        }
        return value;
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
