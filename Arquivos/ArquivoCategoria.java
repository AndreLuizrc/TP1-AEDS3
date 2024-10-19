package Arquivos;

import Objetos.ParNomeId;
import Objetos.Categoria;

public class ArquivoCategoria extends Arquivos.Arquivo<Categoria> {

    Arquivo<Categoria> arqCategoria;
    HashExtensivel<ParNomeId> indiceIndiretoParNomeIdCategoria;

    public ArquivoCategoria() throws Exception {
        super("categorias", Categoria.class.getConstructor());
        indiceIndiretoParNomeIdCategoria = new HashExtensivel<>(
            ParNomeId.class.getConstructor(), 
            4, 
            ".\\dados\\indiceNomeCategoria.hash_d.db", 
            ".\\dados\\indiceNomeCategoria.hash_c.db"
        );
    }

    @Override
    public int create(Categoria c) throws Exception {
        int id = super.create(c);
        indiceIndiretoParNomeIdCategoria.create(new ParNomeId(c.getNome(), id));

        return id;
    }

    public Categoria read(String nome) throws Exception {
        System.out.println(ParNomeId.hash(nome));
        ParNomeId pci = indiceIndiretoParNomeIdCategoria.read(ParNomeId.hash(nome));
        if(pci == null){
            System.out.println("entrei");
            return null;
        }
            
        return read(pci.getId());
    }
    
    public boolean delete(String nome) throws Exception {
        ParNomeId pci = indiceIndiretoParNomeIdCategoria.read(ParNomeId.hash(nome));
        if(pci != null) 
            if(delete(pci.getId())) 
                return indiceIndiretoParNomeIdCategoria.delete(ParNomeId.hash(nome));
        return false;
    }

    @Override
    public boolean update(Categoria novaCategoria) throws Exception {
        Categoria categoriaVelha = read(novaCategoria.getNome());
        if(super.update(novaCategoria)) {
            if(novaCategoria.getNome().compareTo(categoriaVelha.getNome())!=0) {
                indiceIndiretoParNomeIdCategoria.delete(ParNomeId.hash(categoriaVelha.getNome()));
                indiceIndiretoParNomeIdCategoria.create(new ParNomeId(novaCategoria.getNome(), novaCategoria.getId()));
            }
            return true;
        }
        return false;
    }
}

