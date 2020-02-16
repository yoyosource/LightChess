package lightchess.stages;

import lightchess.KeyInput;
import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.OptionManager;
import lightchess.render.Button;
import lightchess.render.Fonts;
import lightchess.render.Stage;
import lightchess.render.TextField;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ConnectStage implements Stage {

    private lightchess.render.Button[] buttons = new Button[1];
    private int currentSelection = -1;
    private TextField[] texts = new TextField[1];
    private int textfield = -1;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);

        g.fillRect(0,0,1400,1100);
        for (int i = 0; i < buttons.length; i++) {
            if (i == currentSelection) {
                buttons[i].setSelected(true);
            } else {
                buttons[i].setSelected(false);
            }
            buttons[i].render(g);
        }
        g.drawOval(50, 50, 50 ,50);
        g.setColor(Color.darkGray);
        g.fillRect(100, 100,550, 900);
        g.fillRect(750, 100,550, 900);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 75), Color.orange, "Join", 300, 180);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 75), Color.orange, "Host", 950, 180);
        for (int i = 0; i < texts.length; i++) {
            if (i == textfield) {
                texts[i].setSelected(true);
            } else {
                texts[i].setSelected(false);
            }
            texts[i].render(g);
        }

    }

    @Override
    public void tick() {
        if(KeyInput.wasPressed(KeyEvent.VK_ESCAPE)){
            LightChess.draw.setStage(0);
        }
        boolean intersects = false;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1,1))) {
                currentSelection = i;
                intersects = true;
            }

        }

        if (!intersects) {
            currentSelection = -1;
        }


        if (MouseInput.wasPressed(1)) {
            if (currentSelection == 0) {
                LightChess.draw.setStage(0);
            }
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1,1))) {
                    textfield = i;
                }

            }
        }
        for (int i = 0; i < 256; i++) {
            if(KeyInput.wasPressed(i)){
                if(i == KeyEvent.VK_BACK_SPACE){
                    if(texts[textfield].getText().length() == 0) continue;
                    texts[textfield].setText(texts[textfield].getText().substring(0, texts[textfield].getText().length() -1));
                    continue;
                }
                if(i == KeyEvent.VK_SHIFT) continue;
                if(i == KeyEvent.VK_ENTER) continue;
                if(i == KeyEvent.VK_SPACE) {
                    texts[textfield].setText(texts[textfield].getText() + " ");
                    continue;
                }
                if(KeyInput.isKeyDown(KeyEvent.VK_SHIFT)){
                    texts[textfield].setText(texts[textfield].getText() + KeyEvent.getKeyText(i).toUpperCase());
                }else{
                    texts[textfield].setText(texts[textfield].getText() + KeyEvent.getKeyText(i).toLowerCase());
                }

            }
        }
    }

    @Override
    public void start() {
        buttons[0] = new Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "←", 55, 87);
        texts[0] = new TextField(new Font("Old English Text MT", Font.PLAIN, 45), Color.white, Color.BLACK, "", 150, 250, 450, 100);
    }

    @Override
    public void end() {

    }
}
