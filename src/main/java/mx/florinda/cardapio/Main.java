package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    static void main(String[] args) {

        Database dataBase = new SQLDataBase();

        List<ItemCardapio> listaItens = dataBase.listaItensCardapio();
        listaItens.forEach(System.out::println);

        int total = dataBase.totalItensCardapio();
        System.out.println("Total de itens no cardapio: " + total);

//        var novoItemCardapio = new ItemCardapio(21, "Tacos de Carnitas", "Tacos recheiados com carnes tenra",
//                ItemCardapio.CategoriaCardapio.PRATOS_PRINCIPAIS, new BigDecimal(25.9), null);
//
//        dataBase.adicionarItemCardapio(novoItemCardapio);

    }
}
