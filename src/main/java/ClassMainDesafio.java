import mx.florinda.cardapio.Database;
import mx.florinda.cardapio.ItemCardapio;

import java.util.List;

public class ClassMainDesafio {

    static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();



    }
}
