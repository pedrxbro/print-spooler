package server;

import model.PrintJob;
import util.PrintingQueue;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        PrintingQueue queue = new PrintingQueue();
        LogManager log = new LogManager();

        // Pool de Threads para as impressoras
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Printer("HP PRETA HOL IP 142", queue, log));
        pool.execute(new Printer("BROTHER COLOR COND IP 72", queue, log));
        pool.execute(new Printer("RICOH COLOR MKT IP 232", queue, log));

        // Inicia o servidor para receber conexões de clientes
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.register("Servidor iniciado na porta " + PORT);

            while (true) {
                Socket client = serverSocket.accept();

                new Thread(() -> {
                    try (ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
                        PrintJob job = (PrintJob) in.readObject();
                        queue.add(job);
                        log.register("Job adicionado: " + job);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            client.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}