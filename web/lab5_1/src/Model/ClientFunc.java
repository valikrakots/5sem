/*
Client thread realization
 */


package Model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

class ClientFunc extends Thread{
    private Socket socket;
    private BufferedReader in;
    public boolean running = true;

    public ClientFunc(String addr, int port) throws IOException {
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.out.println(e);
        }
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        start();
        Scanner sc = new Scanner(System.in);
        new Thread(new Runnable() {
            public void run(){
                while(true){
                    System.out.println("Print q to exit");
                    String msg = sc.nextLine();

                    if (msg.equals("q")) {
                        try {
                            running = false;
                            System.out.println("Good bye");
                            System.exit(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();
    }






    @Override
    public void run() {
        String str;
        try {
            while (running) {
                str = in.readLine();
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }



}
