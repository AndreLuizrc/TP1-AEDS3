package Utils;

import java.util.Scanner;

import Arquivos.Arquivo;
import Arquivos.ArquivoCategoria;
import Objetos.Categoria;
import Objetos.Tarefas;
import java.time.LocalDate;

public class MenuTarefas{
        
    Arquivo<Tarefas> arqTarefas;
    private static Scanner console = new Scanner(System.in);

    public MenuTarefas() throws Exception {
        arqTarefas = new Arquivo<>("tarefas", Tarefas.class.getConstructor());
    }

    public void menu() throws Exception{

        int opcao;
        do {

            System.out.println("AEDsIII");
            System.out.println("-------");
            System.out.println("\n> Início > Tarefas");
            System.out.println("1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    // buscarCategoria();
                    break;
                case 2:
                    incluirTarefa();
                    break;
                case 3:
                    // alterarCategoria();
                    break;
                case 4:
                    // excluirCategoria();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }


    public void incluirTarefa()throws Exception{
        String nome;
        
        System.out.println("\nInclusão de tarefa: ");
        System.out.println("\nDigite o nome da Tarefa que deseja incluir: ");
        nome = console.nextLine();

        Tarefas novaTarefa = new Tarefas(nome);
        arqTarefas.create(novaTarefa);

    
    }
}   
