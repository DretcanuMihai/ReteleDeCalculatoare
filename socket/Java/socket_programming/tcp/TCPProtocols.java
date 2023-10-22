package socket_programming.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class TCPProtocols {
    public static final String DEFAULT_IP="127.0.0.1";
    public static final int DEFAULT_PORT=8888;

    public static int receiveIntASCII(Socket socket) {
        try {
            InputStream in = socket.getInputStream();

            StringBuilder number = new StringBuilder();
            byte[] b = new byte[1];

            while (true) {
                int i_read=in.read(b);
                if(i_read!=1)
                    continue;
                String character=new String(b,0,1, StandardCharsets.US_ASCII);
                if(character.equals(" "))
                    break;
                number.append(character);
            }
            return Integer.parseInt(number.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void sendIntASCII(Socket socket, int number){
        try {
            OutputStream out=socket.getOutputStream();

            String toSendString=Integer.toString(number);
            toSendString+=" ";

            byte[] toSend=toSendString.getBytes(StandardCharsets.US_ASCII);

            out.write(toSend);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static short receiveShort(Socket socket){
        try {
            InputStream in=socket.getInputStream();
            byte[] bytes_read=new byte[2];
            if(in.read(bytes_read)!=2)
                return 0;
            ByteBuffer byteBuffer=ByteBuffer.allocate(2);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(bytes_read[0]);
            byteBuffer.put(bytes_read[1]);
            return byteBuffer.getShort(0);

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void sendShort(Socket socket,short nr){
        ByteBuffer byteBuffer=ByteBuffer.allocate(2);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.putShort(nr);
        byte[] to_send=byteBuffer.array();
        try {
            OutputStream out = socket.getOutputStream();
            out.write(to_send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
