package StreamsAndNIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

class Server{

    Selector clientSelector;
    public Server(int port) throws IOException {
        //preamble
        clientSelector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), port));
        ssc.register(clientSelector, SelectionKey.OP_ACCEPT);

        //handling
        while(true){
            try{
                while(clientSelector.select(50) == 0);
                Set<SelectionKey> readySet = clientSelector.selectedKeys();
                for(Iterator<SelectionKey> it=readySet.iterator();it.hasNext();){
                    final SelectionKey key = it.next();
                    it.remove();
                    //Handling client connections
                    if(key.isAcceptable()){
                        //Take the Socket of client and obtain it only when we can ready from the client
                        SocketChannel clientSocket = ssc.accept();
                        clientSocket.configureBlocking(false);
                        clientSocket.register(clientSelector, SelectionKey.OP_READ);
                    }
                    //Reading from clients
                    else if(key.isReadable()){



                    }
                }
            } catch(IOException e){System.out.println(e);}
        }
    }
}


public class Main {
    public static void main(String[] args) throws IOException{
        //Begin single threaded server
        new Server(8080);
    }
}
