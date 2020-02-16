package lightchess.board;

import lightchess.board.implemented.*;

import java.awt.*;

public class Board {

    private Piece[][] pieces = new Piece[8][8];

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
        return pieces[y][x].equals(new Piece(PieceColor.UNDEFINED));
    }

    public void move(Position from, Position to) {

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
    public void render(Graphics2D g){

    }

}
