package backend;

import java.io.DataOutputStream;
import java.net.*;

public class AnnouncementFromServer {

    public static void showLobbyToEach() {

        for (Socket soc : GameServerTest.sockets.values()) {
            try {
                
                DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
                dout.writeUTF("\n\n\n");
                dout.flush();

                for (Player p : GameLobby.LP.values()) {

                    dout.writeUTF("Player name: " + p.name + "\nPlayer level: " + p.level);
                    dout.flush();

                }
            } catch (Exception e) {
            }

        }

    }
}