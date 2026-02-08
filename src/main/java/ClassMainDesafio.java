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


        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(61l);
        String mensagem = optionalItem.map(ItemCardapio::toString).orElse("Não encontrado");
        System.out.println(mensagem);

    }
}
