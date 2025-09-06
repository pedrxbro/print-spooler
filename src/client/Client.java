package client;

import model.PrintJob;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String server = "localhost";
        int port = 12345;

        // Espera 2 segundos antes de iniciar
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean connected = false;

        while (!connected) {
            try (Socket socket = new Socket(server, port)) {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                // Cria um Job de impressão
                PrintJob printJob = new PrintJob(1, "documento.pdf", 4);
                out.writeObject(printJob);
                out.flush();

                System.out.println("Job enviado: " + printJob);
                connected = true;

            } catch (Exception e) {
                System.out.println("Erro ao conectar. Tentando novamente em 2 segundos...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
