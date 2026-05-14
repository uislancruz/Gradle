package mx.florinda.cardapio;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorItensCardapio {
    static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        httpServer.createContext("/itensCadapio.json", exchange -> {
            OutputStream responseBody = exchange.getResponseBody();
        responseBody.write("{}".getBytes());});


        System.out.println("Servidor criado com sucesso");
        httpServer.start();
    }
}
