package Model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    /*
    Client thread.
    */


    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);
        SocketChannel client = SocketChannel.open(addr);

        while (true){

            ByteBuffer myBuffer = ByteBuffer.allocate(100);
            myBuffer.clear();
            client.read(myBuffer);
            myBuffer.flip();
            String v = new String(myBuffer.array());
            System.out.println("\nGot message from server: " + v);

        }


    }
}







/*new Thread(new Runnable() {

            public void run(){
                System.out.print("To exit print q ");
                while(true){
                    Scanner sc = new Scanner(System.in);
                    String msg = sc.nextLine();

                    if (msg.equals("q")) {
                        System.out.println("Good bye");
                        System.exit(0);
                    }
                }
            }
        }).start();*/

