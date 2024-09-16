:round_pushpin: Checklist Obrigatório:  
O trabalho possui um índice direto implementado com a tabela hash extensível? SIM :white_check_mark:  
A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro? SIM :white_check_mark:  
A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto? SIM :white_check_mark:  
A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro? SIM :white_check_mark:  
A operação de exclusão marca o registro como excluído e o remove do índice direto? SIM :white_check_mark:  
O trabalho está funcionando corretamente? SIM :white_check_mark:  
O trabalho está completo? SIM :white_check_mark:  
O trabalho é original e não a cópia de um trabalho de outro grupo? SIM :white_check_mark:  

// Descrevam todas as classes criadas e os seus métodos

// Sobre o Trabalho

// Descrever o objetivo do trabalho (GPT)

// Descrevam um pouco o esforço: vocês implementaram todos os requisitos? Houve alguma operação mais difícil? Vocês enfrentaram algum desafio na implementação? Os resultados foram alcançados? ... A ideia, portanto, é relatar como foi a experiência de desenvolvimento do TP. Aqui, a ideia é entender como foi para vocês desenvolver este TP.

:round_pushpin: Estruturação Geral  
-Estrutura de Dados na Classe  
id (INT): Identificador único para cada instância da classe.  
Nome (STRING): Nome descritivo associado à instância.  
Data de Criação (LOCALDATE): Data em que a instância foi criada.  
Data de Conclusão (LOCALDATE): Data em que a instância foi concluída.  
Status (STRING): Indicador textual do estado atual da instância (por exemplo, "ativo", "concluído", "pendente").  
Prioridade (BYTE): Nível de prioridade da instância, representado como um valor numérico.  

-Estrutura do Registro  
Cada objeto será representado como um vetor de bytes, preparado para ser armazenado na memória secundária (em forma de arquivo). A conversão dos registros em bytes será realizada usando as classes ByteArrayInputStream, ByteArrayOutputStream, DataInputStream e DataOutputStream, que facilitam a leitura e escrita dos dados no formato binário adequado. A estrutura do vetor de bytes será definida da seguinte forma:

id = (INT)  
Nome = (STRING UTF-8)  
Data de Criação = (INT)  
Data de Conclusão = (INT)  
Status = (STRING UTF-8)  
Prioridade = (BYTE)  

-Estrutura do Arquivo  
O arquivo de armazenamento conterá um cabeçalho e uma sequência de registros. A estrutura do arquivo será organizada da seguinte forma:  

● Cabeçalho:  
Último ID Registrado: Um inteiro que armazena o último ID utilizado até o momento.

● Registros:  
Lápide (BYTE): Indicador de remoção lógica, onde 0 significa ativo e 1 significa removido.  
Tamanho do Registro (SHORT): Um valor de 2 bytes que indica o tamanho do registro em bytes.  
Registro (Vetor de Bytes): A representação binária do objeto, conforme descrito na estrutura de registro.  

[Último ID] -> [Lápide 1] -> [Tamanho do Registro 1] -> [Registro 1 em Bytes] -> [Lápide 2] -> [Tamanho do Registro 2] -> [Registro 2 em Bytes] -> ... -> EOF



