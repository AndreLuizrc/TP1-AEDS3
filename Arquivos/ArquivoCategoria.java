package Arquivos;

import Objetos.ParNomeId;
import Objetos.Categoria;

import java.io.RandomAccessFile;
import java.util.ArrayList;

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

    //@Override
    public int create(Categoria c) throws Exception {
        int id = super.create(c);
        indiceIndiretoParNomeIdCategoria.create(new ParNomeId(c.getNome(), id));

        return id;
    }

    public Categoria read(String nome) throws Exception {
        //System.out.println(ParNomeId.hash(nome));
        ParNomeId pni = indiceIndiretoParNomeIdCategoria.read(ParNomeId.hash(nome));
        //System.out.println("Entrei " + pni.getNome());
        if(pni == null){
            //System.out.println("entrei");
            return null;
        }
            
        return read(pni.getId());
    }
    
    public boolean delete(String nome) throws Exception {
        
        ParNomeId pni = indiceIndiretoParNomeIdCategoria.read(ParNomeId.hash(nome));
        //System.out.println("Entrei " + pni.getNome());
        if(pni != null){
            if(delete(pni.getId())){
                return indiceIndiretoParNomeIdCategoria.delete(ParNomeId.hash(nome));
            }
        } 
            
        return false;
    }

    //@Override
    public boolean update(Categoria novaCategoria, String nome) throws Exception {
        Categoria categoriaVelha = read(nome);
        if(super.update(novaCategoria)) {
            if(novaCategoria.getNome().compareTo(categoriaVelha.getNome())!=0) {
                indiceIndiretoParNomeIdCategoria.delete(ParNomeId.hash(categoriaVelha.getNome()));
                indiceIndiretoParNomeIdCategoria.create(new ParNomeId(novaCategoria.getNome(), novaCategoria.getId()));
            }
            return true;
        }
        return false;
    }

    public ArrayList<Categoria> getAllCategories()throws Exception{
        ArrayList<Categoria> lista = new ArrayList<>();
        RandomAccessFile file = super.getPointer();

        Categoria obj;
        byte tomb;
        short len;
        byte[] b;

        if(file != null){
            //System.out.println("entrei");
            file.seek(4);

            while(file.getFilePointer() < file.length()){

                obj = new Categoria();
                tomb = file.readByte();
                len = file.readShort();
                b = new byte[len];
                file.read(b);


                if(tomb == ' '){
                        obj.fromByteArray(b);
                        lista.add(obj);
                }
            }
        }
        
        return lista;
    }
}

