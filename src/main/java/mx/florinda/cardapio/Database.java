package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.BEBIDAS;

public class Database {

    public List<ItemCardapio> listaItensCardapio(){
        List<ItemCardapio> itens = new ArrayList<ItemCardapio>();

        ItemCardapio refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves", """ 
                Suco de limão, que parece tamarindo mas tem gosto de groselha""",
                BEBIDAS, new BigDecimal(2.99), null);

        itens.add(refrescoDoChaves);

        return itens;
    }
}
