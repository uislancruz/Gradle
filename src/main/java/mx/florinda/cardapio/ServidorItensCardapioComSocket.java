package mx.florinda.cardapio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorItensCardapioComSocket {
    static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Subiu o servidor!");


            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    InputStream clienteIS = clientSocket.getInputStream();
                    StringBuilder requestBuilder = new StringBuilder();

                    int data;
                    do {
                        data = clienteIS.read();
                        requestBuilder.append((char) data);
                    } while (clienteIS.available() > 0);

                    String request = requestBuilder.toString();
                    System.out.println(request);

                    Path path = Path.of("itensCardapio.json");
                    String json = Files.readString(path);

                    OutputStream clienteOS = clientSocket.getOutputStream();
                    PrintStream clienteOut = new PrintStream(clienteOS);

                    clienteOut.println("HTTP/1.1 200 OK");
                    clienteOut.println("Content-Type: application/json; charset=utf-8");
                    clienteOut.println();
                    clienteOut.println(json);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
