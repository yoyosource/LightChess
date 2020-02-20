package lightchess.networking;

import lightchess.LightChess;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket ss;
    Socket connection;
    String name;

    public Server(int port, String name){
        this.name = name;
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSs() {
        return ss;
    }

    public boolean waitForClient(){
        try {
            ss.accept();
            LightChess.draw.setStage(5);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
