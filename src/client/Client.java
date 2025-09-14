package client;

import model.PrintJob;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {
    private static final int MAX_JOBS = 10;

    public static void main(String[] args) throws InterruptedException {
        String server = "localhost";
        int port = 12345;
        Random random = new Random();

        // Espera 2 segundos antes de iniciar
        Thread.sleep(2000);

        for (int i = 0; i < MAX_JOBS; i++) {
            try (Socket socket = new Socket(server, port);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                int idJob = i + 1;
                String fileName = "Documento_" + idJob + ".pdf";
                int pageCount = random.nextInt(30) + 1;

                PrintJob printJob = new PrintJob(idJob, fileName, pageCount);
                out.writeObject(printJob);
                out.flush();

                System.out.println("Job enviado: " + printJob);

            } catch (Exception e) {
                System.out.println("Erro ao enviar job " + i + ". Tentando novamente em 2 segundos...");
                e.printStackTrace();
                Thread.sleep(2000);
                i--; // tenta enviar o mesmo job novamente
            }
        }
    }
}