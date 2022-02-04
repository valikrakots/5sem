/*
Server thread realization
 */

package Model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerFunc extends Thread{



    private Socket socket;

    private PrintStream out;
    private BufferedReader in;


    private String msg;
    private int millis;

    public ServerFunc(Socket socket) throws IOException {
        Scanner scanner = new Scanner(System.in);
        this.socket = socket;
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.print("Time to sleep: ");
        int mil = Integer.parseInt(scanner.nextLine().toString());
        System.out.print("Message: ");
        String msg = scanner.nextLine();
        this.msg = msg;
        this.millis = mil;
        start();
    }

    @Override
    public void run() {
        while(true) {
            out.print(msg + "\n");
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                out.close();
                in.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
