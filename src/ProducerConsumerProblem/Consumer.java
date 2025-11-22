package ProducerConsumerProblem;

/**
  Classe Consumidor
 
  - Estende Thread.
  - Chama o método consumir() do Buffer.
 */

public class Consumer extends Thread {

    private final Buffer buffer;
    private final String nameConsumer;
    private static final int MAXIMUN_QUANTITY = 12; // consume até 12 linhas

    public Consumer(String nameConsumer, Buffer buffer) {
        this.nameConsumer = nameConsumer;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= MAXIMUN_QUANTITY; i++) {
            try {
                int item = buffer.consume(nameConsumer);
                
                // simulação do tempo de processamento
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
