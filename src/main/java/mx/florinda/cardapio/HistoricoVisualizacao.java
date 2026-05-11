package mx.florinda.cardapio;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoricoVisualizacao {

    private final Database database;
    private final Map<ItemCardapio, LocalDateTime> visualizacao = new HashMap<>();

    public HistoricoVisualizacao(InMemoryDatabase database) {
        this.database = database;
    }

    public void registrarVisualizacao(Long itemId){
        Optional<ItemCardapio> optionalItemCardapio = database.itemCardapioPorId(itemId);
        if(optionalItemCardapio.isEmpty()){
            System.out.println("Item não encontrado"+ itemId);
            return;
        }

        ItemCardapio itemCardapio = optionalItemCardapio.get();
        LocalDateTime agora = LocalDateTime.now();
        visualizacao.put(itemCardapio, agora);
        System.out.printf("'%s' visualizado em 's%' \n", itemCardapio.nome(), agora);

    }
}
