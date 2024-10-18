package Arquivos;

import Objetos.ParNomeId;
import Objetos.Categoria;

//import Objetos.Tarefas;

public class ArquivoCategoria extends Arquivos.Arquivo<Categoria> {
    Arquivo<Categoria> arqCategorias;
    HashExtensivel<ParNomeId> indiceIndiretoCategoria;

    public ArquivoCategoria() throws Exception {
        super("categorias", Categoria.class.getConstructor());
    }

    public void createIndex(Categoria cat) throws Exception{
        ParNomeId pNI = new ParNomeId(cat.nome, cat.id);
        System.out.println("alow");
        indiceIndiretoCategoria.create(pNI);
        ParNomeId ex = indiceIndiretoCategoria.read(pNI.getId());
        System.out.println(ex.getNome());
    }

    public Categoria read(String nome){

        

        return null;
    }
}