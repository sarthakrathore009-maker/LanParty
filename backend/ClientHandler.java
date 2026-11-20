package backend;

import java.io.DataInputStream;
import java.net.Socket;

class ClientHandlerFor {
    public void setFor(Socket soc, GameLobby L, int counter, Player P) {

        ClientHandler CH = new ClientHandler(soc, L, counter, P);

        Thread T = new Thread(CH);

        T.start();
        

    }
}

public class ClientHandler implements Runnable {

    private int C;
    private Player P;
    private Socket soc;
    private GameLobby L;

    public ClientHandler(Socket soc, GameLobby L, int C, Player P) {
        this.soc = soc;
        this.C = C;
        this.L = L;
        this.P = P;

    }

    public void run() {
        try {

            DataInputStream din = new DataInputStream(soc.getInputStream());

            P.name = din.readUTF();
            P.level = din.readInt();
            P.playerID = C;

            L.addLobby(P);
            AnnouncementFromServer.showLobbyToEach();

        } catch (Exception e) {
            System.out.println("ClientHandler " + e.getMessage());

        }

    }

}
