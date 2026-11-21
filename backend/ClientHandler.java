package backend;

import java.io.DataInputStream;
import java.net.Socket;

class ReaderServer {
    Socket soc;
    Player P;
    GameLobby L;
    DataInputStream din;

    ReaderServer(Socket soc, Player P, GameLobby L,DataInputStream din) {
        this.soc = soc;
        this.P = P;
        this.L = L;
        this.din=din;

    }

    public void readerS() {

        try {
            while (true) {
                String response = din.readUTF();
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println("\nPlayer: " + P.name + " Left\n");
            L.removeLobby(P);
            GameServerTest.sockets.remove(P.playerID);
            try {
                soc.close();
            } catch (Exception w) {

            }
            AnnouncementFromServer.showLobbyToEach();

        }

    }
}

public class ClientHandler implements Runnable {
    private Socket soc;
    private GameLobby L;
    int C;
    Player P;

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
            ReaderServer RS = new ReaderServer(soc, P, L,din);
            RS.readerS();

        } catch (Exception e) {
            System.out.println("ClientHandler " + e.getMessage());
            GameServerTest.sockets.remove(C);
            try {
                soc.close();
            } catch (Exception ex) {
            }
        }

    }

}