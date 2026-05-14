package net;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client323 {
    public static void main(String[] args) {
        try {
        	Socket s = new Socket("127.0.0.1", 8731);
            
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected");
                    System.exit(0);
                }
            }).start();

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                out.println(input);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
