package Interfaces;

public interface Registro {
    public void setId(int i);
    public int getId();
    public byte[] toByteArray() throws Exception;
    public void fromByteArray(byte[] b) throws Exception;
}
