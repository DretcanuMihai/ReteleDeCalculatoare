package tcp.sum_numbers_short;

import socket_programming.tcp.TCPProtocols;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    static private String SERVER_IP;
    static private int SERVER_PORT;
    static public void main(String[] args){
        SERVER_IP= TCPProtocols.DEFAULT_IP;
        SERVER_PORT= TCPProtocols.DEFAULT_PORT;

        System.out.println("1.Create Socket;\n2.Connect Socket;");
        Socket clientSocket;
        try {
            clientSocket=new Socket(Inet4Address.getByName(SERVER_IP),SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        communicate(clientSocket);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    static private void communicate(Socket clientSocket){
        System.out.println("Communicating with server...");

        Scanner in=new Scanner(System.in);

        while(true){
            System.out.println("Nr=");
            short nr=in.nextShort();
            TCPProtocols.sendShort(clientSocket,nr);
            if(nr==0)
                break;
        }

        short result= TCPProtocols.receiveShort(clientSocket);

        System.out.println("Result="+ result);

        System.out.println("Finished communicating with server");
    }
}
