package lightchess.board;

public enum PieceColor {

    WHITE(0),
    BLACK(1),

    UNDEFINED(-1);

    private int b;

    private PieceColor(int color) {
        this.b = color;
    }

    public int getColor() {
        return b;
    }

    @Override
    public String toString() {
        if (b == 0) {
            return "W";
        } else if (b == 1) {
            return "B";
        }
        return " ";
    }

}
