import java.io.File;
import java.time.LocalDate;
//import Arquivos.Arquivo;
import Arquivos.ArquivoCategoria;
import Objetos.Tarefas;
import Utils.Status;

public class IO {

    public static void main(String[] args) {
        ArquivoCategoria arqCategorias;

        Tarefas t1 = new Tarefas(1, "Comprar mantimentos", LocalDate.of(2023, 9, 1));
        Tarefas t2 = new Tarefas(2, "Estudar para o exame", LocalDate.of(2023, 8, 25));
        Tarefas t3 = new Tarefas(3, "Estudar para a prova de Aeds 3", LocalDate.of(2023, 9, 3));

        try {
            // Deleta os arquivos de dados e hash para iniciar com um ambiente limpo
            new File(".\\dados\\tarefas.db").delete();
            new File(".\\dados\\tarefas.hash_d.db").delete();
            new File(".\\dados\\tarefas.hash_c.db").delete();

            // Inicializa o arquivo utilizando a classe Tarefas
            arqCategorias = new ArquivoCategoria("tarefas", Tarefas.class.getConstructor());

            // Criar tarefas no arquivo
            arqCategorias.create(t1);
            arqCategorias.create(t2);
            arqCategorias.create(t3);

            // Testa a leitura da tarefa 1
            Tarefas t = arqCategorias.read(1);
            if (t != null) {
                System.out.println("Tarefa encontrada: " + t + "\n");
            } else {
                System.out.println("\nTarefa não encontrada!");
            }

            // Testa a leitura da tarefa 3
            t = arqCategorias.read(3);
            if (t != null) {
                System.out.println("Tarefa encontrada: " + t + "\n");
            } else {
                System.out.println("\nTarefa não encontrada!");
            }

            // sempre setar a data de conclusão quando alterar o status da tarefa para
            // concluído
            t3.setStatus(Status.CONCLUIDO);
            t3.setDoneAt(LocalDate.of(2023, 9, 5));

            t3.setPriority((byte) 3);

            arqCategorias.update(t3);

            t = arqCategorias.read(3);
            if (t != null) {
                System.out.println("Tarefa encontrada: " + t + "\n");
            } else {
                System.out.println("\nTarefa não encontrada!");
            }

            arqCategorias.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// cls && javac IO.java && java IO > saida.txt
// git clean -f