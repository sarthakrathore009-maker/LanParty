package frontend;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            System.out.print("Enter Your Name: ");
            String name = in.next();

            System.out.print("Enter Your Level: ");
            int level = in.nextInt();

            Socket soc = new Socket("localhost", 1010);
            System.out.println("Connected to Server!");

            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
            DataInputStream din = new DataInputStream(soc.getInputStream());

            dout.writeUTF(name);
            dout.flush();
            dout.writeInt(level);
            dout.flush();
            while (true) {

                String response = din.readUTF();
                System.out.println(response);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
