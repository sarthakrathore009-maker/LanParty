package frontend;

import java.net.*;
import java.io.*;
import java.util.Scanner;

class GameClientTest implements Runnable {

    public static Scanner in = new Scanner(System.in);
    public Socket soc;
    public static String name;
    public static int level;
    public static DataOutputStream dout;
    public static DataInputStream din;

    public void GameClientConnector() {
        try {

            System.out.print("Enter Your Name: ");
            name = in.next();

            System.out.print("Enter Your Level: ");
            level = in.nextInt();

            Socket soc = new Socket("localhost", 1010);
            this.soc = soc;
            System.out.println("Connected to Server!");

        } catch (Exception e) {

            System.out.println("Error from Client side " + e.getMessage());

        }

    }

    public void run() {

        GameClientConnector();

        WriterClient WC = new WriterClient(soc);
        Thread TW = new Thread(WC);
        TW.start();
        
        ReaderClient RC = new ReaderClient(soc);
        Thread TR = new Thread(RC);
        TR.start();

    }

}

class WriterClient implements Runnable {
    private Socket soc;

    WriterClient(Socket soc) {
        this.soc = soc;

    }

    public void writer() {
        try {

            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());

            dout.writeUTF(GameClientTest.name);
            dout.flush();
            dout.writeInt(GameClientTest.level);
            dout.flush();

            while (true) {
                int choice;
                System.out.print("Enter 1 for Send Message to server");
                choice = GameClientTest.in.nextInt();
                if (choice == 1) {

                    dout.writeUTF("Hello from:" + GameClientTest.name);
                    dout.flush();
                }

            }
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public void run() {
        writer();

    }

}

class ReaderClient implements Runnable {

    private Socket soc;

    ReaderClient(Socket soc) {
        this.soc = soc;

    }

    public void reader() {
        try {
            DataInputStream din = new DataInputStream(soc.getInputStream());

            while (true) {
                String response = din.readUTF();
                System.out.println(response);
            }

        } catch (Exception e) {

            System.out.println("Error from ReaderClient " + e.getMessage());

        }

    }

    public void run() {
        reader();

    }

}

public class GameClient {
    public static GameClientTest GCT = new GameClientTest();

    public static void main(String[] args) {

        Thread T = new Thread(GCT);
        T.start();

    }
}
