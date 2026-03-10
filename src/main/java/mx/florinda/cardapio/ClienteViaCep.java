package mx.florinda.cardapio;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ClienteViaCep {
    static void main(String[] args) throws Exception {

        URL url = new URL("https://viacep.com.br/ws/01001000/json/");
        URLConnection urlConnection = url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }

    }
}