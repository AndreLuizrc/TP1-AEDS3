package Objetos;

import Interfaces.Registro;
import Utils.Status;

import java.io.*;
import java.time.LocalDate;

public class Tarefas implements Registro{
    private int id;
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

    public Tarefas() {
        this.id = 0;
        this.nome = "";
        this.createdAt = LocalDate.now();
        this.doneAt = null;
        this.status = Status.PENDENTE;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }

    public void setDoneAt(LocalDate doneAt) {
        this.doneAt = doneAt;
    }
    public LocalDate getDoneAt() {
        return doneAt;
    }

    @Override
    public void setId(int i) {
        this.id = i;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.writeInt((int) this.createdAt.toEpochDay());

        // Tratar o caso de doneAt ser null
        if (this.doneAt != null) {
            dos.writeBoolean(true);
            dos.writeInt((int) this.doneAt.toEpochDay());
        } else {
            dos.writeBoolean(false);
        }

        dos.writeUTF(this.status.toString());
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] b) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);

        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.createdAt = LocalDate.ofEpochDay(dis.readInt());

        boolean hasDoneAt = dis.readBoolean();
        if (hasDoneAt) {
            this.doneAt = LocalDate.ofEpochDay(dis.readInt());
        } else {
            this.doneAt = null;
        }

        this.status = Status.valueOf(dis.readUTF());
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
