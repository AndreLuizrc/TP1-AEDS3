package Objetos;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import Interfaces.RegistroHashExtensivel;

public class ParIdId implements RegistroHashExtensivel<ParIdId> {
    private int id1; // chave
    private int id2;     // valor
    private final short TAMANHO = 8;  // tamanho em bytes

    public ParIdId() {
        this.id1 = -1;
        this.id2 = -1;
    }

    public ParIdId(int id1, int id2) throws Exception {
        this.id1 = id1;
        this.id2 = id2;
    }

    public int getId1() {
        return this.id1;
    }

    public int getId2() {
        return this.id2;
    }

 
    @Override
    public int hashCode() {
        return this.id1;
    }

    public short size(){
        return this.TAMANHO;
    }

    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bao);
        dos.writeInt(this.id1);
        dos.writeInt(this.id2);
        return bao.toByteArray();
    }

    public void fromByteArray(byte[] byteArray) throws IOException{
        ByteArrayInputStream bai = new ByteArrayInputStream(byteArray);
        DataInputStream dis = new DataInputStream(bai);
        this.id1 = dis.readInt();
        this.id2 = dis.readInt();
    }
    
    
}
