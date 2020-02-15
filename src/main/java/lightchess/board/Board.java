package lightchess.board;

import lightchess.board.implemented.*;

public class Board {

    private Piece[][] pieces = new Piece[8][8];

    private String defaultBoard = "WR|WN|WB|WQ|WK|WB|WN|WR|WP|WP|WP|WP|WP|WP|WP|WP||||||||||||||||||||||||||||||||BP|BP|BP|BP|BP|BP|BP|BP|BR|BN|BB|BQ|BK|BB|BN|BR";

    private Board() {
        String[] strings = defaultBoard.split("\\|");
        if (strings.length != 64) {
            throw new IllegalArgumentException("Board is not 64 tiles big. (" + strings.length + ")");
        }
        for (int i = 0; i < strings.length; i++) {
            int x = i % 8;
            int y = (i - x) / 8;
            pieces[x][y] = getPiece(strings[i]);
        }
    }

    private Board(String s) {

    }

    private Piece getPiece(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() != 2) {
            return null;
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
        return null;
    }

    public Board createBoard() {
        return new Board();
    }

    public Board createBoard(String s) {
        return new Board(s);
    }

    public void move(Position from, Position to) {

    }

}
