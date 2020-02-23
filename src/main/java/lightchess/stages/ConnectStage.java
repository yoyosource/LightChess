package lightchess.stages;

import lightchess.KeyInput;
import lightchess.LightChess;
import lightchess.MouseInput;
import lightchess.networking.Client;
import lightchess.networking.Server;
import lightchess.render.Button;
import lightchess.render.Fonts;
import lightchess.render.Stage;
import lightchess.render.TextField;
import lightchess.utils.CheckIntersection;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectStage implements Stage {

    private lightchess.render.Button[] buttons = new Button[3];
    private int currentSelection = -1;
    private TextField[] texts = new TextField[3];
    private int textfield = -1;
    private boolean[] wasselected = {false, false, false};
    Server s = null;
    Client c = null;
    private int currentpoints = 0;

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,1400,1100);
        g.setColor(Color.darkGray);
        g.fillRect(100, 100,550, 900);
        g.fillRect(750, 100,550, 900);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 75), Color.orange, "Join", 300, 180);
        Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 75), Color.orange, "Host", 950, 180);
        for (int i = 0; i < buttons.length; i++) {
            if (i == currentSelection) {
                buttons[i].setSelected(true);
            } else {
                buttons[i].setSelected(false);
            }
            buttons[i].render(g);
        }

        g.drawOval(50, 50, 50 ,50);
        for (int i = 0; i < texts.length; i++) {
            if (i == textfield) {
                texts[i].setSelected(true);
            } else {
                texts[i].setSelected(false);
                if(texts[i].getText().equals("")){
                    texts[i].setText(texts[i].getStandarttext());
                    wasselected[i] = false;
                }
            }
            texts[i].render(g);
        }
        if(s != null){
            try {
                g.setColor(Color.GRAY);
                g.fillRect(0,0,1400,1100);
                InetAddress add = InetAddress.getLocalHost();
                Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 100), Color.BLACK, add.getHostAddress(), 600);
                String str = "";
                for (int i = 0; i < currentpoints; i += 60) {
                    if(i > 1){
                        str += ".";
                    }
                }
                Fonts.string(g, new Font("Old English Text MT", Font.PLAIN, 100), Color.BLACK, "Waiting for Client" + str, 287, 400);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void tick() {
        if(s == null){
            if(KeyInput.wasPressed(KeyEvent.VK_ESCAPE)){
                LightChess.draw.setStage(0);
            }
            boolean intersects = false;
            for (int i = 0; i < buttons.length; i++) {
                if (CheckIntersection.intersects(buttons[i], MouseInput.getX(), MouseInput.getY())) {
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
                if(currentSelection == 2){
                    s = new Server(4999, texts[2].getText());
                }
                if(currentSelection == 1){
                    c = new Client(texts[1].getText(), 4999);
                }
                for (int i = 0; i < texts.length; i++) {
                    if (CheckIntersection.intersects(texts[i], MouseInput.getX(), MouseInput.getY())) {
                        textfield = i;
                        if(!wasselected[i]){
                            texts[i].setText("");
                            wasselected[i] = true;
                        }
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
                        texts[textfield].setText(texts[textfield].getText() + ((char)i + "").toUpperCase());
                    }else{
                        texts[textfield].setText(texts[textfield].getText() + ((char)i + "").toLowerCase());
                    }

                }
            }
        }else{
            currentSelection = -1;
            currentpoints++;
            if(currentpoints == 240) currentpoints = 0;
        }

    }

    @Override
    public void start() {
        buttons[0] = new Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "←", 55, 87);
        buttons[1] = new Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "Connect!", 250, 800);
        buttons[2] = new Button(new Font("Old English Text MT", Font.PLAIN, 45),new Font("Old English Text MT", Font.PLAIN, 55),
                new Color(255,103,0, 155), new Color(255,103,0), "Host", 950, 800);
        texts[0] = new TextField(new Font("Old English Text MT", Font.PLAIN, 45), Color.white, Color.BLACK, "Username", 150, 250, 450, 100);
        texts[1] = new TextField(new Font("Old English Text MT", Font.PLAIN, 45), Color.white, Color.BLACK, "Host Ip", 150, 550, 450, 100);
        texts[2] = new TextField(new Font("Old English Text MT", Font.PLAIN, 45), Color.white, Color.BLACK, "Username", 800, 250, 450, 100);
    }

    @Override
    public void end() {

    }
}
