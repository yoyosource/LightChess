package lightchess.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private PrintWriter output;
    private BufferedReader input;

    private boolean running = true;

    private boolean player = false;

    public Client(String ip, int port) {
        try {
            connect(new Socket(ip, port));
        } catch (IOException e) {

        }
    }

    Client(Socket socket) {
        connect(socket);
    }

    private void connect(Socket socket) {
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            return;
        }

        Runnable runnable = () -> {
            while (running) {
                try {
                    String s = input.readLine();
                    if (s == null) {
                        continue;
                    }
                    if (s.equals("CLOSE")) {
                        close();
                        break;
                    }
                    System.out.println(s);
                } catch (IOException e) {

                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void send(String s) {
        output.println(s);
    }

    public void close() {
        send("CLOSE");
        running = false;
    }

    public void setPlayer() {
        player = true;
    }

}
