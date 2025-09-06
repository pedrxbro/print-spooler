package util;

import model.PrintJob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PrintingQueue {
    private BlockingQueue<PrintJob> queue = new LinkedBlockingQueue<>();

    // Adiciona um trabalho de impressão à fila (bloqueia se a fila estiver cheia)
    public void add(PrintJob printJob) throws InterruptedException {
        queue.put(printJob);
    }

    // Remove o próximo trabalho de impressão da fila (bloqueia se a fila estiver vazia)
    public PrintJob remove() throws InterruptedException {
        return queue.take();
    }
}
