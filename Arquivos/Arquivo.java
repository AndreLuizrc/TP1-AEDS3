package Arquivos;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

import Interfaces.Registro;
import Objetos.ParEnderecoId;

public class Arquivo<T extends Registro> {
    final int TAM_CABECALHO = 4;
    RandomAccessFile arquivo;
    String nomeArquivo;
    Constructor<T> construtor;
    HashExtensivel<ParEnderecoId> indiceDireto;

    public Arquivo(String na, Constructor<T> c) throws Exception {
        File d = new File(".\\dados");
        if(!d.exists())
            d.mkdir();

        this.nomeArquivo = ".\\dados\\"+na;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        if(arquivo.length()<TAM_CABECALHO) {
            // inicializa o arquivo, criando seu cabecalho
            arquivo.writeInt(0);
        }
        indiceDireto = new HashExtensivel<>(
                ParEnderecoId.class.getConstructor(),
                10,
                ".\\dados\\diretorio.hash.db",
                ".\\dados\\cestos.hash.db");

    }

    public int create(T obj) throws Exception {
        arquivo.seek(0);
        int proximoID = arquivo.readInt() + 1;
        arquivo.seek(0);
        arquivo.writeInt(proximoID);
        obj.setId(proximoID);

        long endereco = arquivo.length();

        byte[] b = obj.toByteArray();
        arquivo.writeByte(' '); // lapide
        arquivo.writeShort(b.length); // tamanho do registro
        arquivo.write(b);

        indiceDireto.create(new ParEnderecoId(proximoID, endereco));

        return obj.getId();
    }

    public T read(int id) throws Exception {
        T obj;
        short tam;
        byte[] b;
        byte lapide;
        ParEnderecoId par = indiceDireto.read(id);

        if (par != null) {
            // Verificar se o endereço está dentro do comprimento do arquivo
            if (par.getEndereco() < arquivo.length()) {
                arquivo.seek(par.getEndereco());
                obj = construtor.newInstance();
                lapide = arquivo.readByte();
                if (lapide == ' ') {
                    tam = arquivo.readShort();
                    b = new byte[tam];

                    if (arquivo.getFilePointer() + tam <= arquivo.length()) {
                        arquivo.read(b);
                        obj.fromByteArray(b);
                        if (obj.getId() == id)
                            return obj;
                    } else {
                        System.err.println("Tamanho do registro excede os limites do arquivo.");
                    }
                }
            } else {
                System.err.println("Endereço inválido: " + par.getEndereco());
            }
        }
        return null;
    }

    public boolean delete(int id) throws Exception {
       ParEnderecoId par = indiceDireto.read(id);
       if(par == null)
           return false;

       arquivo.seek(par.getEndereco());
       byte lapide = arquivo.readByte();
       if (lapide == ' '){
           arquivo.seek(par.getEndereco());
           arquivo.writeByte('*');
           indiceDireto.delete(id);
           return true;
       }

        return false;
    }

    public boolean update(T novoObj) throws Exception {
        ParEnderecoId par = indiceDireto.read(novoObj.getId());
        if(par == null)
            return false;

        arquivo.seek(par.getEndereco());
        byte lapide = arquivo.readByte();
        short tam = arquivo.readShort();
        byte[] b = new byte[tam];
        arquivo.read(b);

        if(lapide == ' '){
            byte[] b2 = novoObj.toByteArray();
            short tam2 = (short) b2.length;

            if(tam2 <= tam){
                arquivo.seek(par.getEndereco() + 3);
                arquivo.write(b2);
            } else {
                arquivo.seek(par.getEndereco());
                arquivo.writeByte('*');

                long novoEndereco = arquivo.length();
                arquivo.seek(novoEndereco);
                arquivo.writeByte(' ');
                arquivo.writeShort(tam2);
                arquivo.write(b2);

                par.setEndereco(novoEndereco);
                indiceDireto.update(par);
            }
            return true;
        }
        return false;
    }



    public void close() throws IOException {
        arquivo.close();
    }


}
