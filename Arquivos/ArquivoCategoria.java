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
}