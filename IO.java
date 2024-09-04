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
            File f = new File(".\\dados\\tarefas.db");
            f.delete();

            arqTarefas = new Arquivo<>("tarefas.db", Tarefas.class.getConstructor());

            arqTarefas.create(t1);
            arqTarefas.create(t2);
            arqTarefas.create(t3);

            Tarefas t = arqTarefas.read(3);
            if (t != null)
                System.out.println(t);
            else
                System.out.println("\nTarefa não encontrada!");

            t = arqTarefas.read(1);
            if (t != null)
                System.out.println(t);
            else
                System.out.println("\nTarefa não encontrada!");

            t2.setId(2);
            t2.setStatus(Status.CONCLUIDO);
            t2.setDoneAt(LocalDate.now());
            arqTarefas.update(t2);

            t = arqTarefas.read(2);
            if (t != null)
                System.out.println(t);
            else
                System.out.println("\nTarefa não encontrada!");

            arqTarefas.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
