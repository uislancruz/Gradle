import mx.florinda.cardapio.Database;
import mx.florinda.cardapio.ItemCardapio;

import java.util.List;
import java.util.Optional;

public class ClassMainDesafio {

    static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();
        itens.forEach(System.out::println);

        System.out.println("-------------------------");


        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(1l);
        if (optionalItem.isPresent()) {
            ItemCardapio item = optionalItem.get();
            System.out.println(item.toString());
        }else {
            System.out.println("Nenhum item cardapio encontrado");
        }

    }
}
