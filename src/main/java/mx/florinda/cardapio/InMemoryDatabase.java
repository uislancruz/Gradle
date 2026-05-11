package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.*;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class InMemoryDatabase implements Database {

    private final Map<Long, ItemCardapio> itensPorId = new HashMap<>();
    private final Map<ItemCardapio, BigDecimal> auditoriaPrecos = new HashMap<>();

    public InMemoryDatabase() {
        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves", """ 
                Suco de limão, que parece tamarindo mas tem gosto de groselha""",
                BEBIDAS, new BigDecimal(2.99), null);

        itensPorId.put(1l, refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduiche de presunto", """ 
                Sanduiche simples, mas feito com amor""",
                PRATOS_PRINCIPAIS, new BigDecimal(3.55), new BigDecimal(2.99));

        itensPorId.put(2L,sanduicheDoChaves);

        var tortaDaDonaFlorinda = new ItemCardapio(5L, "Torta de frango da Dona Florinda", """ 
                Torta de frango com recheio""",
                PRATOS_PRINCIPAIS, new BigDecimal(5.99), new BigDecimal(3.99));

        itensPorId.put(5L,tortaDaDonaFlorinda);

        var pipocaDoQuico = new ItemCardapio(6L, "Pipoca do quico", """ 
                Pipoca com muita açucar e caramelo""",
                SOBREMESA, new BigDecimal(2.99), new BigDecimal(3.99));

        itensPorId.put(6L,pipocaDoQuico);

        var tonicoDOSeuMadruga = new ItemCardapio(7L, "Tonico do seu madruga", """ 
                Tonico super forte para fortalecer os muscolos""",
                BEBIDAS, new BigDecimal(2.99), null);

        itensPorId.put(7L,tonicoDOSeuMadruga);

        var cafeDaDonaFlorinda = new ItemCardapio(8l, "Café da Dona Florinda", """
                Café forte para começar o dia com energia""",
                BEBIDAS, new BigDecimal("1.99"), new BigDecimal("1.50"));

        itensPorId.put(8L,cafeDaDonaFlorinda);

        var churrosDoChaves = new ItemCardapio(9L, "Churros do Chaves", """ 
                Churros recheados com doce de leite, classicaos e irresistiveis""",
                SOBREMESA, new BigDecimal(4.99), new BigDecimal(3.99));

        itensPorId.put(9L,churrosDoChaves);

        var gelatinaDoNhonho = new ItemCardapio(10l, "Gelatina do Nhonho", """
                Gelatina de varias cores, a sobremesa favorita do Nhonho""",
                SOBREMESA, new BigDecimal(3.50), new BigDecimal(2.99));

        itensPorId.put(10L,gelatinaDoNhonho);

        var boloDaDonaClotilde = new ItemCardapio(11l, "Bolo da Dona Clotilde", """
                Bolo de chocolate com cobertura de brigadeiro""",
                SOBREMESA, new BigDecimal(5.99), new BigDecimal(4.99));

        itensPorId.put(11L,boloDaDonaClotilde);

    }

    @Override
    public List<ItemCardapio> listaItensCardapio(){

        return new ArrayList<>(itensPorId.values());
    }

    @Override
    public Optional <ItemCardapio> itemCardapioPorId(Long itemId){
        ItemCardapio itemCardapio = itensPorId.get(itemId);
        return Optional.ofNullable(itensPorId.get(itemId));
    }

    @Override
    public boolean removerItemCardapio(Long id){
        ItemCardapio removido = itensPorId.remove(id);
        return removido != null;
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long id, BigDecimal novoPreco){
        ItemCardapio item = itensPorId.get(id);
        if(item == null) return false;
        ItemCardapio itemPrecoAlterado = item.alteraPreco(novoPreco);
        itensPorId.put(id, itemPrecoAlterado);
        auditoriaPrecos.put(item, novoPreco);
        return true;
        }

    public void rastroAuditoriaPrecos(){
        System.out.println("\nAuditoria de preços:");
        auditoriaPrecos.forEach((item, preco) -> System.out.printf("'%s: %s => %s'\n", item.nome(), item.preco(), preco));
        System.out.println();
    }

    @Override
    public int totalItensCardapio(){
        return itensPorId.size();
    }

    @Override
    public void adicionarItemCardapio(ItemCardapio item){
        itensPorId.put(item.id(), item);
    }

    }
