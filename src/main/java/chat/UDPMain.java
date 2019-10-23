package chat;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPMain {

    public static void main(String[] args){
        try {
            final int PORT = 6666;
            final InetAddress ADDRESS = InetAddress.getByName("192.168.0.255");
            ExecutorService executorService = Executors.newCachedThreadPool();
            DatagramSocket datagramSocket = new DatagramSocket(PORT);
            UdpConnectionThread udpConnectionThread = new UdpConnectionThread(datagramSocket);
            executorService.submit(udpConnectionThread);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ADDRESS, PORT);
            Scanner scanner = new Scanner(System.in);
            String s = null;
            while ((s = scanner.nextLine())!=null){
                byte[] name = Arrays.copyOf("Sebastian".getBytes(),30);
                byte[] data = Arrays.copyOf(s.getBytes(),1024-30);
                System.arraycopy(name,0,buf,0,30);
                System.arraycopy(data, 0,buf,31, 993);
                packet.setData(buf);
                datagramSocket.send(packet);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
