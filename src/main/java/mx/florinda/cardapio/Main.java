package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    static void main(String[] args) {

        Database dataBase = new SQLDataBase();

        System.out.println("Itens antes da alteração:");
        List<ItemCardapio> listaItens = dataBase.listaItensCardapio();
        listaItens.forEach(System.out::println);

        System.out.println("--------------------------------");

        Long idParaAlterar = 1L;
        BigDecimal novoPreco = new BigDecimal("5.90");

        boolean alterou = dataBase.alterarPrecoItemCardapio(idParaAlterar, novoPreco);

        if (alterou) {
            System.out.println("Preço do item com ID " + idParaAlterar + " alterado com sucesso!");
        } else {
            System.out.println("Item com ID " + idParaAlterar + " não encontrado.");
        }

        System.out.println("--------------------------------");

        System.out.println("Itens depois da alteração:");
        List<ItemCardapio> listaItensAtualizada = dataBase.listaItensCardapio();
        listaItensAtualizada.forEach(System.out::println);

        System.out.println("--------------------------------");

        int total = dataBase.totalItensCardapio();
        System.out.println("Total de itens no cardapio: " + total);
    }
}