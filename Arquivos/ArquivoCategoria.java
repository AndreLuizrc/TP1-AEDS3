package Arquivos;

import Objetos.Tarefas;
import java.lang.reflect.Constructor;
//import Objetos.Tarefas;

public class ArquivoCategoria extends Arquivo<Tarefas> {
    public ArquivoCategoria(String na, Constructor<Tarefas> c) throws Exception {
        super(na, c);
    }
}