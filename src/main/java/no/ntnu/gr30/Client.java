package no.ntnu.gr30;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;

public class Client {
    private final DatagramSocket datagramSocket;
    private final InetAddress inetAddress;
    private final int port;

    public Client(String ipAddress, int port) throws UnknownHostException, SocketException {
        this.datagramSocket = new DatagramSocket();
        this.inetAddress = InetAddress.getByName(ipAddress);
        this.port = port;
    }

    public String sendAndReceive(String string) throws IOException {
        byte[] buffer = string.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, this.inetAddress, port);
        this.datagramSocket.send(datagramPacket);

        buffer = new byte[datagramSocket.getReceiveBufferSize()];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length, this.inetAddress, port);
        this.datagramSocket.receive(response);

        return new String(response.getData(), 0, response.getLength());
    }
}
