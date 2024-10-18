package Arquivos;

import Objetos.ParCategoriaId;
import Objetos.Categoria;

//import Objetos.Tarefas;

public class ArquivoCategoria extends Arquivos.Arquivo<Categoria> {
    Arquivo<Categoria> arqCategorias;
    HashExtensivel<ParCategoriaId> indiceIndiretoCategoria;

    public ArquivoCategoria() throws Exception {
        super("categorias", Categoria.class.getConstructor());
    }
}