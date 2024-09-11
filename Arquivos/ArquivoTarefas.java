package Arquivos;

import Objetos.Tarefas;

public class ArquivoTarefas extends Arquivo<Tarefas> {
    Arquivo<Tarefas> arqTarefas;
    public ArquivoTarefas() throws Exception {
        super("tarefas", Tarefas.class.getConstructor());
    }
}
