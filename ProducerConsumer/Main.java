package ProducerConsumer;

import java.io.*;

/*
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
*/
/*
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
 */
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

    class Reader implements Runnable {
        String id;
        Reader(String id){
            this.id = id;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(id + ": " + Main.this.read());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

    public void beginReader(String id){
        new Thread(new Reader(id)).start();
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
        //Consumer shortcut utilizing Adapter class
        testImpl.beginReader("1");
        testImpl.beginReader("2");
        //Product implementation utilizing anonymous class
        new Thread(new Runnable(){
            public void run(){
                int i = 0;
                while(true){
                    testImpl.write("Message: " + i++);
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){}
                }
            }
        }).start();
    }
}
