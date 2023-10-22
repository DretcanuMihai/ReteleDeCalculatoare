package tcp.sum_numbers;

import socket_programming.tcp.TCPProtocols;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    private static String SERVER_IP= TCPProtocols.DEFAULT_IP;
    private static int SERVER_PORT= TCPProtocols.DEFAULT_PORT;

    public static void main(String[] args){
        Socket clientSocket;
        try {
            System.out.println("1.Create Socket\n2.Connect Socket;");
            clientSocket=new Socket(Inet4Address.getByName(SERVER_IP),SERVER_PORT);
            communicate(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try{
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void communicate(Socket clientSocket){
        System.out.println("Communicating with server");

        while(true){
            Scanner in=new Scanner(System.in);
            System.out.println("Nr=");
            int nr=in.nextInt();
            TCPProtocols.sendIntASCII(clientSocket,nr);
            if(nr==0)
                break;
        }

        int result= TCPProtocols.receiveIntASCII(clientSocket);
        System.out.println("Result="+Integer.toString(result));

        System.out.println("Finished communicating with server");

    }
}
