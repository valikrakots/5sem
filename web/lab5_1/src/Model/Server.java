/*
Server initialized
 */

package Model;

import Controller.Exception.LoggerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) throws IOException {
        Logger logger = null;
        try {


            try(FileInputStream ins = new FileInputStream("C:\\Users\\valll\\Documents\\programming\\5 сем\\web\\lab5_1\\log.config")){
                LogManager.getLogManager().readConfiguration(ins);
                logger = Logger.getLogger(Server.class.getName());
            }catch (Exception e){
                throw new Controller.Exception.LoggerException("Logger config error: " + e.getMessage(), e);
            }
            ServerSocket serverSocket = new ServerSocket(4040);
            System.out.println("Server is working");
            System.out.println("Waiting for clients...");
            Scanner scanner = new Scanner(System.in);
            logger.log(Level.INFO,"Server working");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.print("New client " +  clientSocket.getInetAddress() +" connected. Do you want to exchange messages with him(1 or 0): ");
                Integer ex = scanner.nextInt();
                if (ex == 1){

                    new ServerFunc(clientSocket);
                    logger.log(Level.INFO,"New client connected");
                }
            }
        } catch (IOException | LoggerException e) {
            logger.log(Level.INFO,"An error ocuried. Server is shutting down.");
            e.printStackTrace();
        }
    }

}
