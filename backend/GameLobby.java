package backend;

import java.util.HashMap;

public class GameLobby {

    public static HashMap<Integer, Player> LP = new HashMap<>();

    public void addLobby(Player P) {

        LP.put(P.playerID, P);

    }

    public void removeLobby(Player P) {

        LP.remove(P.playerID);
    }

    public void showLobby() {
        for (Player p : LP.values()) {
            System.out.printf("Player name: %s\nPlayer level: %d\n\n", p.name, p.level);
        }
    }

}
