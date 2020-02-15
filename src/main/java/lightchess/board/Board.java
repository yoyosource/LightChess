package lightchess.board;

public class Board {

    private Piece[][] pieces = new Piece[8][8];

    private Board() {

    }

    private Board(String s) {

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
