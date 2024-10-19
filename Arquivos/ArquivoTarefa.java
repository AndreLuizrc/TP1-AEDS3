package Arquivos;

import Objetos.ParNomeId;
import Objetos.Tarefas;

public class ArquivoTarefa extends Arquivos.Arquivo<Tarefas> {

    Arquivo<Tarefas> arqTarefas;
    HashExtensivel<ParNomeId> indiceIndiretoParNomeIdTarefas;

    public ArquivoTarefa() throws Exception {
        super("tarefas", Tarefas.class.getConstructor());
        indiceIndiretoParNomeIdTarefas = new HashExtensivel<>(
            ParNomeId.class.getConstructor(), 
            4, 
            ".\\dados\\indiceNomeTarefas.hash_d.db", 
            ".\\dados\\indiceNomeTarefas.hash_c.db"
        );
    }

    @Override
    public int create(Tarefas c) throws Exception {
        int id = super.create(c);
        indiceIndiretoParNomeIdTarefas.create(new ParNomeId(c.getNome(), id));

        return id;
    }

    public Tarefas read(String nome) throws Exception {
        System.out.println(ParNomeId.hash(nome));
        ParNomeId pni = indiceIndiretoParNomeIdTarefas.read(ParNomeId.hash(nome));
        if(pni == null){
            System.out.println("entrei");
            return null;
        }
            
        return read(pni.getId());
    }
    
    public boolean delete(String nome) throws Exception {
        ParNomeId pni = indiceIndiretoParNomeIdTarefas.read(ParNomeId.hash(nome));
        if(pni != null) 
            if(delete(pni.getId())) 
                return indiceIndiretoParNomeIdTarefas.delete(ParNomeId.hash(nome));
        return false;
    }

    @Override
    public boolean update(Tarefas novaTarefa) throws Exception {
        Tarefas tarefaVelha = read(novaTarefa.getNome());
        if(super.update(novaTarefa)) {
            if(novaTarefa.getNome().compareTo(tarefaVelha.getNome())!=0) {
                indiceIndiretoParNomeIdTarefas.delete(ParNomeId.hash(tarefaVelha.getNome()));
                indiceIndiretoParNomeIdTarefas.create(new ParNomeId(novaTarefa.getNome(), novaTarefa.getId()));
            }
            return true;
        }
        return false;
    }
}

