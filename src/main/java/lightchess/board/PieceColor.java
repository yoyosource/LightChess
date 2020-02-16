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

    public static PieceColor getColor(int i) {
        if (i == -1) {
            return UNDEFINED;
        } else if (i  == 0) {
            return WHITE;
        } else if (i == 1) {
            return BLACK;
        }
        return UNDEFINED;
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
