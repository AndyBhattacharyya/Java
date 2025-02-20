package FileIO;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //Choose our image directory to exist in our target class package
        File rootdir = new File(Main.class.getProtectionDomain() .getCodeSource() .getLocation() .toURI());
        File imagedir = new File(rootdir, "images");
        //ignore boolean since we only care if it's there, perhaps create functionality to delete preexisting one
        imagedir.mkdirs();
        //Image transferring
        try(
        ServerSocket fs = new ServerSocket(8080);
        ) {
            int i = 0;
            while(true){
                try(
                        Socket client = fs.accept();
                        InputStream cin = client.getInputStream();
                        FileOutputStream fout = new FileOutputStream(new File(imagedir, "image" + i++ + ".jpg"));
                        ){
                    while (cin.available() > 0) {
                        fout.write(cin.read());
                        //not sure if this is necessarry
                        fout.flush();
                    }
                }catch(IOException e){System.out.println("error2");}
            }
        } catch (IOException e) {System.out.println("error1");}
    }
}
