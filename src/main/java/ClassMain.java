import mx.florinda.cardapio.Database;
import mx.florinda.cardapio.ItemCardapio;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class ClassMain {

    static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();

        //Saber quantos itens de cada cateforia realkmente tem no cardapio
        //Categoria => Quantidade

        Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new HashMap<>();
        for (ItemCardapio item : itens) {
            ItemCardapio.CategoriaCardapio categoria = item.categoria();

            if (!itensPorCategoria.containsKey(categoria)) {
                itensPorCategoria.put(categoria, 1);
            }else {
                Integer quantidadeAnterior = itensPorCategoria.get(categoria);
                itensPorCategoria.put(categoria, quantidadeAnterior + 1);
            }

        }
        System.out.println(itensPorCategoria);


    }
}
