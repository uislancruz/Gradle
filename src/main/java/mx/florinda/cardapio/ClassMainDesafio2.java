package mx.florinda.cardapio;

public class ClassMainDesafio2 {

        static void main(String[] args) {

            InMemoryDatabase database = new InMemoryDatabase();

            //Preciso de um historico de visualização do cardapio

            HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
            historico.registrarVisualizacao(1L);
            historico.registrarVisualizacao(3L);
            historico.registrarVisualizacao(4L);
            historico.registrarVisualizacao(6L);

        }
    }

