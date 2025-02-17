package ProducerConsumer;

import java.io.*;

class Write implements Runnable{

    Main tmp;
    Write(Main tmp){
        this.tmp = tmp;
    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            tmp.write("Message: " + i++);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
}
class Read implements Runnable{

    Main tmp;
    String id;
    Read(Main tmp, String id){
        this.tmp = tmp;
        this.id = id;
    }
    @Override
    public void run() {
        while(true){
            try {
                System.out.println(id+ ": "+tmp.read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

public class Main{

    //utilize a stream to read and write too
    PipedReader pipedReader;
    BufferedReader in;
    PrintWriter out;
    Main() throws IOException{
        pipedReader = new PipedReader();
        in = new BufferedReader(pipedReader);
        out =  new PrintWriter(new PipedWriter(pipedReader));
    }

    synchronized public void write(String message){
        out.println(message);
    }

    synchronized public String read() throws IOException{
        return in.readLine();
    }

    public static void main(String[] args) throws IOException {
        Main testImpl = new Main();
        new Thread(new Write(testImpl)).start();
        new Thread(new Read(testImpl, "1")).start();
        new Thread(new Read(testImpl,"2")).start();
    }
}
