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
                    alterarCategoria();
                    break;
                case 4:
                    excluirCategoria();
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

        System.out.println("\nPesquisa de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja pesquisar: ");
        nome = filler(console.nextLine());

        Categoria obj = arqCategorias.read(nome);
        if(obj != null){
            System.out.println(obj);
        }else{
            System.out.println("Categoria nao encontrada");
        }
        
    }

    public void incluirCategoria() throws Exception {
        String nome;

        System.out.println("\nInclusão de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja incluir: ");
        nome = filler(console.nextLine());

        Categoria novaTarefa = new Categoria(nome); 

        System.out.println(novaTarefa.getNome());

        arqCategorias.create(novaTarefa);
    }

    public void excluirCategoria()throws Exception{
        String nome;

        System.out.println("\nExclusao de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja excluir: ");

        nome = filler(console.nextLine());

        if(arqCategorias.delete(nome)){
            System.out.println("Categoria excluida com sucesso!!\n");
        }else{
            System.out.println("Categoria nao encontrada\n");
        }

    }

    public void alterarCategoria()throws Exception{
        System.out.println("\nAlteracao de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja alterar: ");

        String nome = filler(console.nextLine());

        Categoria obj = arqCategorias.read(nome);

        if(obj != null){
            System.out.println(obj);
            System.out.println("\nQual informação gostaria de alterar?");
            System.out.println("1 - Nome");
            System.out.println("0 - Voltar");

            int option;
            System.out.print("Opção: ");
            try {
                option = Integer.valueOf(console.nextLine());
            } catch (NumberFormatException e) {
                option = -1;
            }

            switch (option) {
                case 1:
                    System.out.println("Digite o novo nome da Categoria");
                    String novoNome = filler(console.nextLine());
                    obj.setNome(novoNome);
                    if(arqCategorias.update(obj, nome)){
                        System.out.println("Atualizacao realizada com sucesso");
                    }else{
                        System.out.println("ERRO");
                    }
                    break;
                case 2:
                    System.out.println("Atualizacao cancelada");
                    break;
                default:
                    System.out.println("Atualizacao cancelada");
                    break;
            }
        }else{
            System.out.println("Categoria nao encontrada");
        }

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
