package lightchess.networking;

import lightchess.LightChess;

import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket s;

    public Client(){

    }
    public boolean connect(String ip, int port) {
        try {
            s = new Socket(ip, port);
            LightChess.draw.setStage(5);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
