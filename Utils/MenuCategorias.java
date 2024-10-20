package Utils;

import java.util.Scanner;
import Arquivos.ArquivoCategoria;
import Arquivos.ArquivoTarefa;
import Objetos.Categoria;
import Objetos.ParIdId;

import java.text.Normalizer;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MenuCategorias {

    ArquivoCategoria arqCategorias;
    private Scanner console = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

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

        System.out.println("\nPesquisa de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja pesquisar: ");

        String nome = console.nextLine();
        String nomeLimpo = tratarNome(nome);

        Categoria obj = arqCategorias.read(nomeLimpo);
        if (obj != null) {
            System.out.println(obj);
        } else {
            System.out.println("Categoria nao encontrada");
        }

    }

    public void incluirCategoria() throws Exception {

        System.out.println("\nInclusão de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja incluir: ");

        String nome = console.nextLine();
        String nomeLimpo = tratarNome(nome);

        Categoria novaCategoria = new Categoria(nomeLimpo);        

        arqCategorias.create(novaCategoria);
        System.out.println("Categoria criada com sucesso!!\n");
    }

    public void excluirCategoria() throws Exception {

        System.out.println("\nExclusao de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja excluir: ");

        String nome = console.nextLine();
        String nomeLimpo = tratarNome(nome);

        ArquivoTarefa arqTarefas = new ArquivoTarefa();

        ArrayList<ParIdId> pii = arqTarefas.getAllStacksFromCategorie(arqCategorias.read(nomeLimpo).getId());
            if(pii.size() > 0){
                System.out.println("Esta categoria nao pode ser deletada, pois ainda ha tarefas vinculadas a ela");
            }else{
                if (arqCategorias.delete(nomeLimpo)) {
                    System.out.println("Categoria excluida com sucesso!!\n");
                } else {
                    System.out.println("Categoria nao encontrada\n");
                }
            }
    }

    public void alterarCategoria() throws Exception {
        System.out.println("\nAlteracao de categoria: ");
        System.out.println("\nDigite o nome da categoria que deseja alterar: ");

        String nome = console.nextLine();
        String nomeLimpo = tratarNome(nome);
        ArquivoTarefa arqTarefas = new ArquivoTarefa();

        Categoria obj = arqCategorias.read(nomeLimpo);

        ArrayList<ParIdId> pii = arqTarefas.getAllStacksFromCategorie(arqCategorias.read(nomeLimpo).getId());
            if(pii.size() > 0){
                System.out.println("Esta categoria nao pode ser alterada, pois ainda ha tarefas vinculadas a ela");
            }else{
                if (obj != null) {
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
        
                            String nomeNovo = console.nextLine();
                            nomeNovo = tratarNome(nomeNovo);
        
                            obj.setNome(nomeNovo);
                            if (arqCategorias.update(obj, nomeLimpo)) {
                                System.out.println("Atualizacao realizada com sucesso");
                            } else {
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
                } else {
                    System.out.println("Categoria nao encontrada");
                }
        
            }

        
    }

    public String tratarNome(String nome) {
        // Trata acentos
        String nomeLimpo = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Preenche para ficar com tamanho exato
        nomeLimpo = filler(nomeLimpo);

        return nomeLimpo;
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

    public String unfiller(String nome) {
        char[] tmp = new char[20];
        int j = 0;
        for (int i = 0; i < 20; i++) {
            if (nome.charAt(i) != '|') {
                tmp[j] = nome.charAt(i);
                j++;
            }
        }
        String fixed = new String(tmp);
        return fixed;
    }


}
