package net;
import java.io.*;
import java.net.*;
import java.util.*;

class Client implements Runnable {
    Socket s;
    BufferedReader in;
    PrintWriter out;
    String name;
    
    static ArrayList<Client> clients = new ArrayList<>();

    Client(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            out.println("Enter Password:");
            String password = in.readLine();
            
            if (!"pokemon".equals(password)) {
                out.println("Wrong Password!");
                System.out.println("Client failed password attempt: " + s.getInetAddress());
                s.close();
                return;
            }

            out.println("Enter Name:");
            name = in.readLine();

            clients.add(this);
            System.out.println("Client Logged in: " + name + " [" + s.getInetAddress() + "]");

            String msg;
            while ((msg = in.readLine()) != null) {
                broadcast("[" + name + "] " + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " disconnected.");
        }
    }

    void broadcast(String message) {
        for (Client c : clients) {
            if (c == this) {
                continue;
            }
            try {
                c.out.println(message);
            } catch (Exception e) {}
        }
    }
}

public class Server323 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(0x221B);
            System.out.println("Server Started");
            System.out.println("Listening on Port: " + ss.getLocalPort()); 
            
            while (true) {
                Socket s = ss.accept();
                System.out.println("New Connection request from: " + s.getInetAddress());
                
                Client c = new Client(s);
                new Thread(c).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
