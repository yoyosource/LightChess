package lightchess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private static int x = -1, y = -1;
    private static final Boolean[] buttons = new Boolean[20];
    private static final Boolean[] lastbuttons = new Boolean[20];


    public MouseInput(){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = false;
            lastbuttons[i] = false;
        }
    }
    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    public static void update(){
        for (int i = 0; i < 10; i++) {
            lastbuttons[i] = buttons[i];
        }
    }
    public static boolean isDown(int button){
        return buttons[button];
    }
    public static boolean wasPressed(int button){
        return isDown(button) && !lastbuttons[button];
    }
    public static boolean wasReleased(int button){
        return !isDown(button) && lastbuttons[button];
    }
}
