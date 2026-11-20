package backend;


public class Player {
    
    String name;
    int level,playerID;

    public Player() {

    }

    public Player(int ID,String s, int l) {
        playerID = ID;
        name = s;
        level = l;

        System.out.println("Data set !!");
    }

}