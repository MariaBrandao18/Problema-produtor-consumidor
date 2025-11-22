package ProducerConsumerProblem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe Buffer
 
  - Recurso compartilhado do problema produtor–consumidor.
  - Usa Semáforos (Semaphore) para controlar:
       * espaços vazios (emptySlots)  -> quantos itens ainda podem ser inseridos
       * itens cheios (fullSlots)     -> quantos itens podem ser consumidos
  - ReentrantLock como mutex para exclusão mútual
    na região crítica.
  - O logger (arquivo texto) registra a operação, visualizando
   as alterações no estado do buffer atravez da classe BufferredWrite.
 */

public class Buffer {

    private final int[] buffer;
    private int in;          // inserção
    private int out;         // remoçã0
    private int count;       // contador atualizado do buffer

    private final Semaphore emptySlots;   
    private final Semaphore fullSlots;    
    private final ReentrantLock mutex;    

    private final BufferedWriter writer;  

    public Buffer(int size, String LogFileName) throws IOException { // será inicializado com valores vazios
        this.buffer = new int[size]; 
        this.in = 0;
        this.out = 0;
        this.count = 0;

        this.emptySlots = new Semaphore(size); // será inicializado com o tamamho das 'permissões'
        this.fullSlots = new Semaphore(0);     // começa vazio
        this.mutex = new ReentrantLock();

        // abre o arquivo de log
        this.writer = new BufferedWriter(new FileWriter(LogFileName, false));
    }

	/**
	 Método para o produtor(Producer) inserir um item no buffer.
	
	 - emptySlots.acquire(): operação P (down) no semáforo.
	 - mutex.lock(): entra na seção crítica.
	 - Atualiza estrutura de dados compartilhada (buffer, in, count).
	 - Registra log.
	 - mutex.unlock(): sai da seção crítica.
	 - fullSlots.release(): operação V (up) no semáforo.
	*/
    public void produce(String producerName, int item) throws InterruptedException {
        // aguarda até existir 1 espaço vazio
        emptySlots.acquire();

        mutex.lock();
        try {
            buffer[in] = item;
            in = (in + 1) % buffer.length;
            count++;

            int availableSpace = buffer.length - count;
            logWrite("Produtor - Inserido um item no buffer – espaços disponíveis: " + availableSpace);
        } finally {
            mutex.unlock();
        }
        // sinaliza que existe mais um item cheio
        fullSlots.release();
    }
    
    /**
      Método para o cosumidor(Consumer) remover um item do buffer.
     
      - fullSlots.acquire(): operação P no semáforo.
      - mutex.lock(): entra na seção crítica.
      - Atualiza estrutura de dados compartilhada.
      - Registra log.
      - mutex.unlock(): sai da seção crítica.
      - emptySlots.release(): sinaliza que agora há pelo menos mais um espaço vazio.
     */

    public int consume(String nameConsumer) throws InterruptedException {
        //aAguarda até existir 1 item para consumir
        fullSlots.acquire();

        int item;
        mutex.lock();
        try {
            item = buffer[out];
            out = (out + 1) % buffer.length;
            count--;

            int availableSpace = buffer.length - count;
            logWrite("Consumidor - Consumido um item no buffer – espaços disponíveis: " + availableSpace);
        } finally {
            mutex.unlock();
        }
        // sinaliza que existe mais um espaço vazio
        emptySlots.release();
        return item;
    }
    
    // Escreve no arquivo do Log
    
    private void logWrite(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
    
    // Fecha a escrita no arquivo do Log

    public void logClose() {
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar o arquivo de log: " + e.getMessage());
        }
    }
}
