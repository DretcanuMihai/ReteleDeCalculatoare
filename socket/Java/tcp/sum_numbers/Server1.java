package tcp.sum_numbers;

import socket_programming.tcp.TCPProtocols;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

    private static String SERVER_IP= TCPProtocols.DEFAULT_IP;
    private static int SERVER_PORT= TCPProtocols.DEFAULT_PORT;

    public static void main(String[] args){

        ServerSocket serverSocket;
        try {
            System.out.println("1.Create Socket;\n2.Bind Socket;\n3.Listen;\n");
            serverSocket=new ServerSocket(SERVER_PORT,5,Inet4Address.getByName(SERVER_IP));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while(true){
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

class ServeAction implements Runnable{

    private Socket clientSocket;

    public ServeAction(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private void serve(){
        System.out.println("Serving client "+clientSocket.getInetAddress().toString());

        int suma=0;
        while(true){
            int nr= TCPProtocols.receiveIntASCII(clientSocket);
            if(nr==0)
                break;
            suma+=nr;
        }

        System.out.println("Sending sum="+Integer.toString(suma)+" to client");

        TCPProtocols.sendIntASCII(clientSocket,suma);

        System.out.println("Finished serving client "+clientSocket.getInetAddress().toString());
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