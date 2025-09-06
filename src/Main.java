import model.PrintJob;
import server.LogManager;
import server.Printer;
import util.PrintingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PrintingQueue queue = new PrintingQueue();
        LogManager logManager = new LogManager();
        PrintJob job = new PrintJob(1, "documento.pdf", 4);
        Printer printer = new Printer("HP PRETA HOL IP 142", queue, logManager);

        new Thread(printer).start();

        queue.add(job);
        logManager.register("Job adicionado: " + job);

    }
}