package lightchess.board;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    private static boolean flip = false;

    public static void setFlip(boolean flip) {
        Position.flip = flip;
    }

    public Position(int x, int y) {
        if (x < 0) {
            throw new IllegalArgumentException("Illegal position");
        }
        if (x > 7) {
            throw new IllegalArgumentException("Illegal position");
        }
        if (y < 0) {
            throw new IllegalArgumentException("Illegal position");
        }
        if (y > 7) {
            throw new IllegalArgumentException("Illegal position");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        if (!flip) {
            return 7 - x;
        }
        return x;
    }

    public int getY() {
        if (!flip) {
            return 7 - y;
        }
        return y;
    }

    public void flipX() {
        x = 7 - x;
    }

    public void flipY() {
        y = 7 - y;
    }

    public void flip() {
        flipX();
        flipY();
    }

    public Position copy() {
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
