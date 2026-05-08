package mx.florinda.cardapio;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteIntesCardapioComSocket {

    static void main(String[] args) throws Exception{

        try (Socket socket = new Socket("localhost", 8000)){
            OutputStream clienteOS = socket.getOutputStream();
            PrintStream clienteOut = new PrintStream(clienteOS);
            clienteOut.println("GET /intesCardapioComSocket HTTP/1.1");
            clienteOut.println();

            InputStream clienteIS = socket.getInputStream();
            Scanner scanner = new Scanner(clienteIS);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }
}
