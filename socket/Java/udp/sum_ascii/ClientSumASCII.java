package udp.sum_ascii;

import socket_programming.udp.UDPProtocols;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Scanner;

public class ClientSumASCII {
    private static String SERVER_IP;
    private static int SERVER_PORT;

    public static void main(String[] args){
        SERVER_IP= UDPProtocols.DEFAULT_IP;
        SERVER_PORT= UDPProtocols.DEFAULT_PORT;
        System.out.println("1.Create Socket");
        DatagramSocket socket;
        try {
            socket=new DatagramSocket();
            communicate(socket);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        socket.close();
    }

    private static void communicate(DatagramSocket socket){
        System.out.println("Communicating with server...");

        Scanner in=new Scanner(System.in);

        UDPProtocols.MyAddress serverAddress=new UDPProtocols.MyAddress(SERVER_IP,SERVER_PORT);
        System.out.print("My number is:");
        UDPProtocols.sendIntASCII(socket,serverAddress,Integer.parseInt(in.next()));
        int sum=UDPProtocols.receiveIntASCII(socket);
        System.out.println("My sum is:"+sum);
        System.out.println("Communication with server ended");
    }
}
