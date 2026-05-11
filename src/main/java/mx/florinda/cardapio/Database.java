package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface Database {
    List<ItemCardapio> listaItensCardapio();

    Optional<ItemCardapio> itemCardapioPorId(Long itemId);

    boolean removerItemCardapio(Long id);

    boolean alterarPrecoItemCardapio(Long id, BigDecimal novoPreco);

    int totalItensCardapio();

    void adicionarItemCardapio(ItemCardapio item);
}
