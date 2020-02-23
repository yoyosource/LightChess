package lightchess.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private String name;

    private List<Client> clients = new ArrayList<>();

    private boolean running = true;

    public Server(int port, String name) {
        this.name = name;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }

        Runnable runnable = () -> {
            while (running) {
                try {
                    Client client = new Client(serverSocket.accept());
                    if (clients.isEmpty()) {
                        client.setPlayer();
                    }
                    System.out.println("Accepted Client");
                    clients.add(client);
                } catch (IOException e) {

                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void close() {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).close();
        }
        running = false;
    }
}
