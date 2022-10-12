package no.ntnu.gr30;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;

public class Client {
    private final InetAddress inetAddress;
    private final int port;
    private DatagramSocket datagramSocket;
    private boolean socketIsOpen;

    public Client(String ipAddress, int port) throws UnknownHostException {
        this.inetAddress = InetAddress.getByName(ipAddress);
        this.port = port;
    }

    public String sendAndReceive(String string) throws IOException {
        if (!socketIsOpen) throw new SocketException();

        byte[] buffer = string.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, this.inetAddress, port);
        datagramSocket.send(datagramPacket);

        buffer = new byte[datagramSocket.getReceiveBufferSize()];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length, this.inetAddress, port);
        datagramSocket.receive(response);

        return new String(response.getData(), 0, response.getLength());
    }

    public void openSocket() throws SocketException {
        this.datagramSocket = new DatagramSocket();
        this.socketIsOpen = true;
    }

    public void closeSocket() {
        this.datagramSocket.close();
        this.datagramSocket = null;
        this.socketIsOpen = false;
    }
}
