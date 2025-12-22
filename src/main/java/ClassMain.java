import mx.florinda.cardapio.Database;
import mx.florinda.cardapio.ItemCardapio;

import java.util.ArrayList;
import java.util.List;

public class ClassMain {

    static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();

        for (ItemCardapio item : itens) {
            System.out.println(item.toString());
        }

    }
}
