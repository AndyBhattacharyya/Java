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
        notify();
        // notifyAll();
    }

    synchronized public String read() throws IOException{
        /*
        * wait() to ensure that we won't block on a read() and hold onto monitor/lock
        * we rely on notify() from writer threads
         */
        try {
            wait();
        } catch(InterruptedException e){
            System.out.println("Interrupted");
        }
        return in.readLine();
    }

    public static void main(String[] args) throws IOException {
        Main testImpl = new Main();
        new Thread(new Write(testImpl)).start();
        /*
        Using multiply consumers works since only one of these threads will awaken as soon as a
        producer calls notify(). However don't use notifyAll(), as its possible 2 consecutive reading threads
        obtain the monitor lock, and block
         */
        new Thread(new Read(testImpl, "1")).start();
        new Thread(new Read(testImpl, "2")).start();
    }
}
