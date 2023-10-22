package socket_programming.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet4Address;

public class UDPServer {
    private static String SERVER_IP;
    private static int SERVER_PORT;

    public static void main(String[] args){

        SERVER_IP= UDPProtocols.DEFAULT_IP;
        SERVER_PORT= UDPProtocols.DEFAULT_PORT;

        System.out.println("1.Create Socket\n2.Bind Socket\n");
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
        //CODE GOES HERE
    }
}
