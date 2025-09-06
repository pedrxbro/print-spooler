package server;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private static final String LOG_FILE = "log_server.txt";

    public synchronized void register(String message){

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            fw.write(LocalDateTime.now().format(formatter) + " - " + message + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
