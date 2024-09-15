import java.io.File;
import java.time.LocalDate;
import Arquivos.Arquivo;
import Objetos.Tarefas;
import Utils.Status;

public class IO {

    public static void main(String[] args) {
        Arquivo<Tarefas> arqTarefas;
        Tarefas t1 = new Tarefas(1, "Comprar mantimentos", LocalDate.of(2023, 9, 1));
        Tarefas t2 = new Tarefas(2, "Estudar para o exame", LocalDate.of(2023, 8, 25));
        Tarefas t3 = new Tarefas(3, "Finalizar o projeto", LocalDate.of(2023, 9, 3));

        try {
            ".\\dados"
            // Deleta os arquivos de dados e hash para iniciar com um ambiente limpo
            new File(".\\dados\\tarefas.db").delete();
            new File(".\\dados\\diretorio.hash.db").delete();
            new File(".\\dados\\cestos.hash.db").delete();

            // Inicializa o arquivo utilizando a classe Tarefas
            arqTarefas = new Arquivo<>("tarefas.db", Tarefas.class.getConstructor());

            // Criar tarefas no arquivo
            arqTarefas.create(t1);
            arqTarefas.create(t2);
            arqTarefas.create(t3);

            // Testa a leitura da tarefa 1
            Tarefas t = arqTarefas.read(1);
            if (t != null) {
                System.out.println("Tarefa encontrada: " + t);
            } else {
                System.out.println("\nTarefa não encontrada!");
            }

            // Testa a leitura da tarefa 3
            t = arqTarefas.read(3);
            if (t != null) {
                System.out.println("Tarefa encontrada: " + t);
            } else {
                System.out.println("\nTarefa não encontrada!");
            }

            // Atualiza a tarefa 2 para concluída
            t2.setStatus(Status.CONCLUIDO);
            t2.setDoneAt(LocalDate.now());
            arqTarefas.update(t2);

            // Testa a leitura da tarefa 2 atualizada
            t = arqTarefas.read(2);
            if (t != null) {
                System.out.println("Tarefa atualizada: " + t);
            } else {
                System.out.println("\nTarefa não encontrada!");
            }

            // Fecha o arquivo de tarefas
            arqTarefas.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
