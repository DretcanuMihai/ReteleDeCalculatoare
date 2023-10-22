package socket_programming.udp;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class UDPProtocols {
    public static final String DEFAULT_IP="127.0.0.1";
    public static final int DEFAULT_PORT=8888;
    public static final int DEFAULT_BUFFER_SIZE=1024;
    public static final int DEFAULT_STRING_BUFFER_SIZE=DEFAULT_BUFFER_SIZE;
    public static final int DEFAULT_INT_BUFFER_SIZE=10;
    public static MyAddress lastSenderAddress=new MyAddress(DEFAULT_IP,DEFAULT_PORT);

    public static class MyAddress{
        public InetAddress ipAddress;
        public int port;

        public MyAddress(InetAddress ipAddress, int port) {
            this.ipAddress = ipAddress;
            this.port = port;
        }

        public MyAddress(String ipAddress, int port) {
            InetAddress address=null;
            try {
                address = Inet4Address.getByName(ipAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            this.ipAddress=address;
            this.port = port;
        }

        @Override
        public String toString() {
            return "{ipAddress=" + ipAddress +
                    ", port=" + port+"}";
        }
    }

    public static String receiveStringASCII(DatagramSocket socket){
        try {
            byte[] buffer=new byte[DEFAULT_STRING_BUFFER_SIZE];
            DatagramPacket datagramPacket=new DatagramPacket(buffer,buffer.length);
            socket.receive(datagramPacket);
            lastSenderAddress=new MyAddress(datagramPacket.getAddress(),datagramPacket.getPort());
            return new String(datagramPacket.getData(),0, datagramPacket.getLength(),
                    StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void sendStringASCII(DatagramSocket socket,MyAddress address,String message){
        try {
            byte[] buffer=message.getBytes(StandardCharsets.US_ASCII);
            DatagramPacket datagramPacket=new DatagramPacket(buffer,buffer.length,address.ipAddress,address.port);
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int receiveIntASCII(DatagramSocket socket) {
        try {
            byte[] buffer=new byte[DEFAULT_INT_BUFFER_SIZE];
            DatagramPacket datagramPacket=new DatagramPacket(buffer,buffer.length);
            socket.receive(datagramPacket);
            lastSenderAddress=new MyAddress(datagramPacket.getAddress(),datagramPacket.getPort());
            String stringNumber=new String(datagramPacket.getData(),0, datagramPacket.getLength(),
                    StandardCharsets.US_ASCII);
            return Integer.parseInt(stringNumber);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void sendIntASCII(DatagramSocket socket,MyAddress address, int number){
        try {
            String stringNumber=Integer.toString(number);
            byte[] buffer=stringNumber.getBytes(StandardCharsets.US_ASCII);
            DatagramPacket datagramPacket=new DatagramPacket(buffer,buffer.length,
                    address.ipAddress,address.port);
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static short receiveShort(DatagramSocket socket){
        try {
            byte[] buffer=new byte[2];
            DatagramPacket datagramPacket=new DatagramPacket(buffer,2);
            socket.receive(datagramPacket);
            lastSenderAddress=new MyAddress(datagramPacket.getAddress(),datagramPacket.getPort());
            //received data -> now translate it
            ByteBuffer byteBuffer=ByteBuffer.allocate(2);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(datagramPacket.getData()[0]);
            byteBuffer.put(datagramPacket.getData()[1]);
            return byteBuffer.getShort(0);

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void sendShort(DatagramSocket socket,MyAddress address,short nr){
        ByteBuffer byteBuffer=ByteBuffer.allocate(2);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.putShort(nr);
        byte[] to_send=byteBuffer.array();
        try {
            DatagramPacket datagramPacket=new DatagramPacket(to_send,2,address.ipAddress, address.port);
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
