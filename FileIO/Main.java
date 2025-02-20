package FileIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Current Directory: " + System.getProperty("user.dir"));
        ServerSocket fs = new ServerSocket(8080);
        Socket client = fs.accept();
        InputStream cin = client.getInputStream();
        FileOutputStream fout = new FileOutputStream("dtest.png");
        System.out.println("This much data to copy: " + cin.available());
        while(cin.available() > 0) {
            fout.write(cin.read());
            //not sure if this is necessarry
            fout.flush();
        }
        fout.close();
    }
}
