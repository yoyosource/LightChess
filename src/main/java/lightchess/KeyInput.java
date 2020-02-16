package lightchess;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private static final Boolean[] keys = new Boolean[256];
    private static final Boolean[] lastkeys = new Boolean[256];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    public static boolean isKeyDown(int i){
        return keys[i];
    }
    public static void update(){
        for (int i = 0; i < 256; i++) {
            lastkeys[i] = keys[i];
        }
    }
    public static boolean wasPressed(int i){
        return isKeyDown(i) && !lastkeys[i];
    }
    public static boolean wasReleased(int i){
        return !isKeyDown(i) && lastkeys[i];
    }
    public KeyInput(){
        for (int i = 0; i < 256; i++) {
            keys[i] = false;
            lastkeys[i] = false;
        }
    }
}
