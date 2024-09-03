package Objetos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import Interfaces.RegistroHashExtensivel;

public class ParEnderecoId implements RegistroHashExtensivel<ParEnderecoId> {

    private int id;
    private long endereco;
    private final short TAMANHO = 12;

    public ParEnderecoId() {
        this(-1, -1);
    }

    public ParEnderecoId(int id, long end) {
        this.id = id;
        this.endereco = end;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public short size(){
        return this.TAMANHO;
    }

    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bao);
        dos.writeInt(id);
        dos.writeFloat(endereco);
        return bao.toByteArray();
    }

    public void fromByteArray(byte[] byteArray) throws IOException{
        ByteArrayInputStream bai = new ByteArrayInputStream(byteArray);
        DataInputStream dis = new DataInputStream(bai);
        this.id = dis.readInt();
        this.endereco = dis.readLong();
    }
}
