import com.sun.source.doctree.SeeTree;
import mx.florinda.cardapio.Database;
import mx.florinda.cardapio.ItemCardapio;

import java.util.*;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class ClassMainDesafio {

    static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();
        itens.forEach(System.out::println);

        System.out.println("-------------------------");


        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(61l);
        String mensagem = optionalItem
                .map(ItemCardapio::toString)
                .orElse("Não encontrado");
        System.out.println(mensagem);

        System.out.println("===========================");

        // PRECISA MANTER AS CATEFORIAS QUE ESTAO EM PROMOCAO

        Set<ItemCardapio.CategoriaCardapio> categoriaPromocao = new TreeSet<>();
        categoriaPromocao.add(SOBREMESA);
        categoriaPromocao.add(ItemCardapio.CategoriaCardapio.ENTRADAS);
        categoriaPromocao.forEach(System.out::println);

        System.out.println("------------------------------");

        Set<ItemCardapio.CategoriaCardapio> categoriaPromocao2 = Set.of(SOBREMESA, ENTRADAS);
        categoriaPromocao2.forEach(System.out::println);

        System.out.println("------------------------------");

        //MELHOR JEITO DE FAZER
        Set<ItemCardapio.CategoriaCardapio> categoriaPromocao3 = EnumSet.of(SOBREMESA, ENTRADAS);
        categoriaPromocao3.add(PRATOS_PRINCIPAIS);
        categoriaPromocao3.forEach(System.out::println);

        //PRECISO DAS DESCRICOES ASSOCIADAS AS CATEGORIAS EM PROMOCAO

        System.out.println("------------------------------");

        Map <ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESA, "O doce perfeito para voce");
        promocoes.put(ENTRADAS, "Comece sua refeição com um toque de sabor");
        System.out.println(promocoes);




    }
}
