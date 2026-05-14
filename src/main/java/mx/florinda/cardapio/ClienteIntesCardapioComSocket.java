package mx.florinda.cardapio;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteIntesCardapioComSocket {

    public static void main(String[] args) throws Exception {

        // CORRIGIDO: porta 8080 para bater com o servidor
        try (Socket socket = new Socket("localhost", 8080)) {
            OutputStream clienteOS = socket.getOutputStream();
            PrintStream clienteOut = new PrintStream(clienteOS);

            // CORRIGIDO: rota para o arquivo itensCardapio.json
            clienteOut.println("GET /itensCardapio.json HTTP/1.1");
            clienteOut.println();
            // opcionalmente poderia enviar cabeçalho Host, mas o servidor não exige

            InputStream clienteIS = socket.getInputStream();
            Scanner scanner = new Scanner(clienteIS);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }
}
