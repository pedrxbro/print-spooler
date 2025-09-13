package server;

import model.PrintJob;
import util.PrintingQueue;

public class Printer implements Runnable {
    private String printerName;
    private PrintingQueue queue;
    private LogManager log;

    public Printer(String printerName, PrintingQueue queue, LogManager log) {
        this.printerName = printerName;
        this.queue = queue;
        this.log = log;
    }

    @Override
    public void run() {
        while(true) {
            try {
                // Puxa um job da fila.
                PrintJob job = queue.remove();

                System.out.println(printerName + " - Iniciando impressão: " + job);
                log.register(printerName + " - Impressão iniciada: " + job);

                // Simula o tempo de impressão (1 segundo por página)
                Thread.sleep(job.getPageCount() * 1000L);

                System.out.println(printerName + "- Impressão concluída: " + job);
                log.register(printerName + " - Impressão concluída: " + job);
            }
            catch (InterruptedException e) {
                break; // Encerra a thread
            }
        }
    }
}
