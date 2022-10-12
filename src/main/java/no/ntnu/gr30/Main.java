package no.ntnu.gr30;

import java.io.IOException;

public class Main {
    private static final String IP_ADDRESS = "129.241.152.12";
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        Client client = new Client(IP_ADDRESS, PORT);

        int howManyTimes = 3;
        for (int i = 0; i < howManyTimes; i++) {
            client.openSocket();

            String response = client.sendAndReceive("task");
            System.out.println(response);

            String result = Analyse.analyseString(response);
            System.out.println(result);

            response = client.sendAndReceive(result);
            System.out.println(response);

            client.closeSocket();
        }
    }
}