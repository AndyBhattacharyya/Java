package SSEServer;

import java.io.*;
import java.net.ServerSocket;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSSE = new ServerSocket(8080);
        while(true) {
            //implicitly started thread upon object invocation
            new SSEMessage(serverSSE.accept());
        }

    }
}
