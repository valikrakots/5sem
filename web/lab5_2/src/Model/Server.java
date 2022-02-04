package Model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Server {

    /*
    Server message.
     */

    static String msg = "Hello";
    static int time = 1000;
    static int mil = 2000;

    public static void main(String[] args) throws IOException, InterruptedException {

        Selector selector = Selector.open();
        ServerSocketChannel servSocket = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);
        servSocket.bind(addr);
        servSocket.configureBlocking(false);
        int ops = servSocket.validOps();
        servSocket.register(selector, ops, (Object)null);
        System.out.println("Server is working");
        boolean written;

        while(true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator iter = keys.iterator();
            written = false;


            while (iter.hasNext()) {
                SelectionKey myKey = (SelectionKey) iter.next();
                if (!myKey.isValid()) {
                    continue;
                }
                if (myKey.isAcceptable()) {
                    SocketChannel client = servSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, 4);
                    System.out.println("Connection Accepted: " + client.getLocalAddress() + "\n");
                }
                else if (myKey.isWritable()) {
                    SocketChannel nsc = (SocketChannel) myKey.channel();
                    byte[] message = msg.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(message);
                    nsc.write(buffer);
                    buffer.clear();
                    written = true;
                }
                iter.remove();
                if (written)
                    Thread.sleep(time);
            }


        }



    }


}

