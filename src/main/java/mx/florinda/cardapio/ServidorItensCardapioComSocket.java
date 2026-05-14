package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorItensCardapioComSocket {

    private static final Logger logger = Logger.getLogger(ServidorItensCardapioComSocket.class.getName());
    private static final Database database = new SQLDataBase();

    public static void main(String[] args) throws Exception {

        Executor executor = Executors.newFixedThreadPool(50);

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            logger.info("Servidor com socket subiu na porta 8080");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(() -> trataRequisicao(clientSocket));
            }
        }
    }

    private static void trataRequisicao(Socket clientSocket) {

        try (clientSocket) {

            InputStream clientIS = clientSocket.getInputStream();
            Scanner scanner = new Scanner(clientIS);

            if (!scanner.hasNextLine()) {
                return;
            }

            // Lê só a primeira linha do HTTP: "GET /caminho HTTP/1.1"
            String requestLine = scanner.nextLine();
            logger.fine("Request line: " + requestLine);

            String[] parts = requestLine.split(" ");
            if (parts.length < 3) {
                responde400(clientSocket.getOutputStream());
                return;
            }

            String method = parts[0];
            String requestURI = parts[1];

            // Ignora o restante dos headers
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break; // linha em branco separa cabeçalhos do corpo
                }
            }

            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            roteiaRequisicao(method, requestURI, scanner, clientOut);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro no servidor", ex);
            throw new RuntimeException(ex);
        }
    }

    private static void roteiaRequisicao(String method,
                                         String requestURI,
                                         Scanner scanner,
                                         PrintStream clientOut) throws Exception {

        logger.info(() -> "Method=" + method + " URI=" + requestURI);

        if ("/itensCardapio.json".equals(requestURI) && "GET".equals(method)) {
            // Lê arquivo JSON
            Path path = Path.of("itensCardapio.json");
            String json = Files.readString(path);

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println("Content-Type: application/json; charset=UTF-8");
            clientOut.println();
            clientOut.println(json);

        } else if ("GET".equals(method) && "/itensCardapio".equals(requestURI)) {
            // Lista vinda do banco
            Gson gson = new Gson();
            String json = gson.toJson(database.listaItensCardapio());

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println("Content-Type: application/json; charset=UTF-8");
            clientOut.println();
            clientOut.println(json);

        } else if ("GET".equals(method) && "/itensCardapio/total".equals(requestURI)) {
            int totalItens = database.totalItensCardapio();

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println("Content-Type: text/plain; charset=UTF-8");
            clientOut.println();
            clientOut.println(totalItens);

        } else if ("POST".equals(method) && "/itensCardapio".equals(requestURI)) {
            // Corpo da requisição como JSON (bem simplificado)
            StringBuilder bodyBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                bodyBuilder.append(line);
            }

            String body = bodyBuilder.toString();

            Gson gson = new Gson();
            ItemCardapio itemCardapio = gson.fromJson(body, ItemCardapio.class);
            database.adicionarItemCardapio(itemCardapio);

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println();

        } else {
            clientOut.println("HTTP/1.1 404 Not Found");
            clientOut.println("Content-Type: text/plain; charset=UTF-8");
            clientOut.println();
            clientOut.println("URI não encontrada: " + requestURI);
        }
    }

    private static void responde400(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println("HTTP/1.1 400 Bad Request");
        out.println();
    }
}