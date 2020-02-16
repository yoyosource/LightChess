package lightchess.networking;

import lightchess.LightChess;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket ss;
    Socket connection;

    public Server(int port){
        try {
            ss = new ServerSocket(port);
            connection = ss.accept();
            LightChess.draw.setStage(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
