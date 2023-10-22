package udp.sum_short;

import socket_programming.udp.UDPProtocols;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet4Address;

public class ServerSumShort {
    private static String SERVER_IP;
    private static int SERVER_PORT;

    public static void main(String[] args){

        SERVER_IP= UDPProtocols.DEFAULT_IP;
        SERVER_PORT= UDPProtocols.DEFAULT_PORT;

        System.out.println("1.Create Socket\n2.Bind Socket");
        DatagramSocket socket;
        try {
            socket=new DatagramSocket(SERVER_PORT, Inet4Address.getByName(SERVER_IP));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        serve(socket);
        socket.close();
    }
    private static void serve(DatagramSocket socket){
        System.out.println("Serving clients...");
        short sum=0;
        while(true){
            short nr=UDPProtocols.receiveShort(socket);
            sum+=nr;
            System.out.println("I received:"+nr+"\nFrom:"+UDPProtocols.lastSenderAddress+"\nSum is now:"+sum);
            UDPProtocols.sendShort(socket,UDPProtocols.lastSenderAddress,sum);
        }
    }
}
