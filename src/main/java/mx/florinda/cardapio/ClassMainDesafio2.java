package mx.florinda.cardapio;
import mx.florinda.cardapio.Database;
import mx.florinda.cardapio.HistoricoVisualizacao;
import mx.florinda.cardapio.ItemCardapio;

import java.util.*;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class ClassMainDesafio2 {

        static void main(String[] args) {

            Database database = new Database();

            //Preciso de um historico de visualização do cardapio

            HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
            historico.registrarVisualizacao(1L);
            historico.registrarVisualizacao(3L);
            historico.registrarVisualizacao(4L);
            historico.registrarVisualizacao(6L);

        }
    }

