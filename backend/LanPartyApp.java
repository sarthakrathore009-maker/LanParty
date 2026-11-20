package backend;

public class LanPartyApp {

    public static void main(String[] args) {

        System.out.println("Hey");

        Player P1 = new Player(101,"Mohan", 23);
        Player P2 = new Player(102,"Rakesh", 65);
        Player P3 = new Player(103,"Shuresh", 43);
        Player P4 = new Player(104,"Rohan", 53);

        GameLobby L1 = new GameLobby();

        L1.addLobby(P1);
        L1.addLobby(P2);
        L1.addLobby(P3);
        L1.addLobby(P4);

        L1.showLobby();

        L1.removeLobby(P4);

        System.out.printf("\nAfter removing %S\n\n",P4.name);



        L1.showLobby();

    }

}
