package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Database {

    public List<ItemCardapio> listaItensCardapio(){
        List<ItemCardapio> itens = new LinkedList<>();

        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves", """ 
                Suco de limão, que parece tamarindo mas tem gosto de groselha""",
                BEBIDAS, new BigDecimal(2.99), null);

        itens.add(refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduiche de presunto", """ 
                Sanduiche simples, mas feito com amor""",
                PRATOS_PRINCIPAIS, new BigDecimal(3.55), new BigDecimal(2.99));

        itens.add(sanduicheDoChaves);

        var tortaDaDonaFlorinda = new ItemCardapio(5L, "Torta de frango da Dona Florinda", """ 
                Torta de frango com recheio""",
                PRATOS_PRINCIPAIS, new BigDecimal(5.99), new BigDecimal(3.99));

        itens.add(tortaDaDonaFlorinda);

        var pipocaDoQuico = new ItemCardapio(1L, "Pipoca do quico", """ 
                Pipoca com muita açucar e caramelo""",
                SOBREMESA, new BigDecimal(2.99), new BigDecimal(3.99));

        itens.add(pipocaDoQuico);

        var tonicoDOSeuMadruga = new ItemCardapio(4L, "Tonico do seu madruga", """ 
                Tonico super forte para fortalecer os muscolos""",
                BEBIDAS, new BigDecimal(2.99), null);

        itens.add(tonicoDOSeuMadruga);

        return itens;
    }
}
