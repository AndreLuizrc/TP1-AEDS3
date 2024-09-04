package Objetos;

import Interfaces.Registro;
import Utils.Status;

import java.io.*;
import java.time.LocalDate;

public class Tarefas implements Registro{
    private final int id;
    private String nome;
    private LocalDate createdAt;
    private LocalDate doneAt;
    private Status status;

    public Tarefas(int id, String nome, LocalDate createdAt) {
        this.id = id;
        this.nome = nome;
        this.createdAt = createdAt;
        this.doneAt = null;
        this.status = Status.PENDENTE;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.flush();
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] b) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Tarefas tarefa = (Tarefas) ois.readObject();

        this.nome = tarefa.nome;
        this.createdAt = tarefa.createdAt;
        this.doneAt = tarefa.doneAt;
        this.status = tarefa.status;
    }

    @Override
    public String toString() {
        return  "\nID........: " + this.id +
                "\nNome......: " + this.nome +
                "\nCreated At: " + this.createdAt.toString() +
                "\nStatus....: " + this.status +
                (this.status == Status.CONCLUIDO ? "\nDone at...: " + this.doneAt.toString() : "");
    }
}
