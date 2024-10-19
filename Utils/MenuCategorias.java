package Utils;

import java.util.Scanner;
import Arquivos.ArquivoCategoria;
import Objetos.Categoria;

public class MenuCategorias {

    ArquivoCategoria arqCategorias;
    private static Scanner console = new Scanner(System.in);

    public MenuCategorias() throws Exception {
        arqCategorias = new ArquivoCategoria();
    }

    public void menu() throws Exception {

        int opcao;
        do {

            System.out.println("AEDsIII");
            System.out.println("-------");
            System.out.println("\n> Início > Categorias");
            System.out.println("1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarCategoria();
                    break;
                case 2:
                    incluirCategoria();
                    break;
                case 3:
                    // alterarTarefa();
                    break;
                case 4:
                    // excluirTarefa();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    public void buscarCategoria() throws Exception {
        String nome;

        System.out.println("\nPesquisa de tarefa: ");
        System.out.println("\nDigite o nome da Categoria que deseja pesquisar: ");
        nome = filler(console.nextLine());

        System.out.println(arqCategorias.read(nome));
    }

    public void incluirCategoria() throws Exception {
        String nome;

        System.out.println("\nInclusão de tarefa: ");
        System.out.println("\nDigite o nome da Tarefa que deseja incluir: ");
        nome = filler(console.nextLine());

        Categoria novaTarefa = new Categoria(nome); 

        System.out.println(novaTarefa.getNome());

        arqCategorias.create(novaTarefa);
    }

    public String filler(String nome) {
        if (nome.length() > 20) {
            throw new IllegalArgumentException("O nome excede o tamanho máximo permitido.");
        }
        char[] filler = new char[20];
        for (int i = 0; i < nome.length(); i++) {
            filler[i] = nome.charAt(i);
        }
        for (int i = nome.length(); i < filler.length; i++) {
            filler[i] = '|';
        }
        String tmp = new String(filler);
        nome = tmp;
        return nome;
    }
}
