package socket_programming.udp;

import java.io.IOException;
import java.net.DatagramSocket;

public class UDPClient {
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

        //CODE HERE

        System.out.println("Communication with server ended");
    }
}
