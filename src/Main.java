import model.PrintJob;
import util.PrintingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PrintingQueue queue = new PrintingQueue();
        queue.add(new PrintJob(1, "documento.pdf", 4));
        System.out.println(queue.remove());

    }
}