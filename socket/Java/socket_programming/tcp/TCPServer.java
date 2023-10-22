package socket_programming.tcp;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static String SERVER_IP;
    private static int SERVER_PORT;

    public static void main(String[] args){

        SERVER_IP= TCPProtocols.DEFAULT_IP;
        SERVER_PORT= TCPProtocols.DEFAULT_PORT;

        System.out.println("1.Create Socket\n2.Bind Socket\n3.Listen");
        ServerSocket serverSocket;
        try {
            serverSocket=new ServerSocket(SERVER_PORT,5,Inet4Address.getByName(SERVER_IP));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while(true){
            System.out.println("4.Accept");
            try {
                Socket clientSocket=serverSocket.accept();
                ServeAction serveAction=new ServeAction(clientSocket);
                Thread thread=new Thread(serveAction);
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ServeAction implements Runnable {

    private Socket clientSocket;

    public ServeAction(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void serve(){

        System.out.println("Serving client:"+clientSocket.getInetAddress().toString());

        //Code here

        System.out.println("Served client:"+clientSocket.getInetAddress().toString());

    }

    @Override
    public void run() {
        try {
            serve();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
