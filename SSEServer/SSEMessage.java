package SSEServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class SSEMessage implements Runnable {

    private Socket client;
    private PrintWriter out;
    public SSEMessage(Socket client) throws IOException {
        this.client = client;
        out = new PrintWriter(client.getOutputStream());
        String httpPreamble = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/event-stream\r\n" +
                "Access-Control-Allow-Origin: *\r\n" +
                "Cache-Control: no-cache\r\n";
        out.println(httpPreamble);
        out.flush();
        new Thread(this).start();
    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            /*
            According to SSE event specification, it's require \r\n\r\n to avoid concatenation
            data: message 1

            data: message 2
             */
            out.println("data: Wassup brotha: " + i++ + "\r\n");
            out.flush();
            try {
                sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Error Occured");
            }
        }
    }
}
