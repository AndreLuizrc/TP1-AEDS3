package Objetos;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import Interfaces.RegistroHashExtensivel;

public class ParNomeId implements RegistroHashExtensivel<ParNomeId> {
    private String nome; // chave
    private int id; // valor
    private final short TAMANHO = 24; // tamanho em bytes

    public ParNomeId() {
        this.nome = "ABCDEFGHIJKLMNQRST";
        this.id = -1;
    }

    public ParNomeId(String nome, int id) throws Exception {
        if (nome == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo.");
        }

        if (nome.length() != 20) {
            throw new IllegalArgumentException("O nome deve ter exatamente 20 caracteres.");
        }

        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return hash(this.nome);
    }

    public short size() {
        return this.TAMANHO;
    }

    public String toString() {
        return "(" + this.nome + ";" + this.id + ")";
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.write(this.nome.getBytes());
        dos.writeInt(this.id);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        byte[] b = new byte[20];
        dis.read(b);
        this.nome = new String(b);
        this.id = dis.readInt();
    }

    public static int hash(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }

        // Usar o hashCode da própria string e ajustar para evitar valores negativos
        int hashValue = nome.hashCode();

        // Usar um número primo grande para modular o valor, se necessário
        hashValue = Math.abs(hashValue % (int) (1e9 + 7));

        return hashValue;
    }

}
