package others;

import schedulling.abstractions.Freezer;
import util.Event;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Logger {
    public static void main(String[] args) throws IOException {
        Freezer fr = new Freezer();
        String pathtoLog = "logs/binary/";
        String description="starting in queue";
        String GUID="%%%0";
        String pseudo="egr";
        Date date=new Date();

        for (int i =0;i<100000; i++){
            Event event = new Event(description, GUID, pseudo, date,0);
            String filename=pathtoLog+i+".bin";
            Path p = Paths.get(filename);

            FileOutputStream fos2 = new FileOutputStream(filename);
            fos2.write(fr.saveEvent(event));
            fos2.close();
        }
    }
}
