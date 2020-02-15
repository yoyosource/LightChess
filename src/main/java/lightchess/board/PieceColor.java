package lightchess.board;

public enum PieceColor {

    WHITE(0),
    BLACK(1);

    private int b;

    private PieceColor(int color) {
        this.b = color;
    }

    public int getColor() {
        return b;
    }

}
