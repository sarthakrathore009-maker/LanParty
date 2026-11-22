package backend;

import java.util.concurrent.ConcurrentHashMap;
import java.net.*;

class GameServerTest implements Runnable {

    public static ConcurrentHashMap<Integer, Socket> sockets = new ConcurrentHashMap<>();
    public static GameLobby L = new GameLobby();

    public void serverConnection() {

        try {

            int counter = 100;
            ServerSocket ss = new ServerSocket(1010);

            while (true) {

                System.out.println("Waiting");
                Socket soc = ss.accept();
                System.out.println("Connected");

                Player P = new Player();
                ClientHandler CH = new ClientHandler(soc, L, counter, P);
                sockets.put(counter, soc);
                Thread T = new Thread(CH);
                T.start();

                counter++;

            }

        }

        catch (Exception e) {
            System.out.println("Client handler error!!");


        }

    }

    public void run() {
        serverConnection();
    }
}

public class GameServer {

    public static void main(String args[]) {

        GameServerTest gst = new GameServerTest();
        Thread T1 = new Thread(gst);
        T1.start();

    }
}
