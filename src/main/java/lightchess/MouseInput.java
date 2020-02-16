package lightchess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter {

    public static int x = -1, y = -1;

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        x = e.getX();
        y = e.getY();
    }
}
