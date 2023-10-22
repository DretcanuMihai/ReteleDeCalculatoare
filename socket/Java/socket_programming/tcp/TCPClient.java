package socket_programming.tcp;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

public class TCPClient {

    private static String SERVER_IP;
    private static int SERVER_PORT;

    public static void main(String[] args){
        SERVER_IP= TCPProtocols.DEFAULT_IP;
        SERVER_PORT= TCPProtocols.DEFAULT_PORT;
        System.out.println("1.Create Socket\n2.Connect Socket");
        Socket clientSocket;
        try {
            clientSocket=new Socket(Inet4Address.getByName(SERVER_IP),SERVER_PORT);
            communicate(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void communicate(Socket clientSocket){
        System.out.println("Communicating with server...");

        //CODE HERE

        System.out.println("Communication with server ended");
    }
}
