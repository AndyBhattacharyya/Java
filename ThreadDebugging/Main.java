package ThreadDebugging;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8000);
        Socket client = listener.accept();
        PrintWriter pout = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        pout.println("Wassup");
        pout.flush();

    }
}
