package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLDataBase implements Database {
    @Override
    public List<ItemCardapio> listaItensCardapio() {
        List<ItemCardapio> itens = new ArrayList<>();

        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio";

        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String categoriaStr = rs.getString("categoria");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal precoPromocional = rs.getBigDecimal("preco_promocional");

                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(categoriaStr);

                var itemCardapio = new ItemCardapio(id, nome, descricao, categoria, preco, precoPromocional);

                itens.add(itemCardapio);
            }

            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int totalItensCardapio() {
        String sql = "SELECT COUNT(*) FROM item_cardapio";
        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int total = 0;
            if (rs.next()) {
                total = rs.getInt(1);

            }
            return total;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void adicionarItemCardapio(ItemCardapio item) {
        String sql = "INSERT INTO item_cardapio (id, nome, descricao, categoria, preco, preco_promocional) VALUES (?,?,?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, item.id());
            ps.setString(2, item.nome());
            ps.setString(3, item.descricao());
            ps.setString(4, item.categoria().name());
            ps.setBigDecimal(5, item.preco());
            ps.setBigDecimal(6, item.precoComDesconto());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, itemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    String categoriaStr = rs.getString("categoria");
                    BigDecimal preco = rs.getBigDecimal("preco");
                    BigDecimal precoPromocional = rs.getBigDecimal("preco_promocional");

                    ItemCardapio.CategoriaCardapio categoria =
                            ItemCardapio.CategoriaCardapio.valueOf(categoriaStr);

                    ItemCardapio itemCardapio =
                            new ItemCardapio(id, nome, descricao, categoria, preco, precoPromocional);

                    return Optional.of(itemCardapio);
                }

                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removerItemCardapio(Long id) {
        String sql = "DELETE FROM item_cardapio WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            int linhasAfetadas = ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long id, BigDecimal novoPreco) {
        String sql = "UPDATE item_cardapio SET preco = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setBigDecimal(1, novoPreco);
            ps.setLong(2, id);

            int linhasAfetadas = ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}