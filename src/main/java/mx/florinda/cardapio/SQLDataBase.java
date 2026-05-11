package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SQLDataBase implements Database{
    @Override
    public List<ItemCardapio> listaItensCardapio() {
        return List.of();
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
        return Optional.empty();
    }

    @Override
    public boolean removerItemCardapio(Long id) {
        return false;
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long id, BigDecimal novoPreco) {
        return false;
    }

    @Override
    public int totalItensCardapio() {
        return 0;
    }

    @Override
    public void adicionarItemCardapio(ItemCardapio item) {

    }
}
