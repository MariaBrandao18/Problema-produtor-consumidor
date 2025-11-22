package ProducerConsumerProblem;

/**
  Classe Producer
 
  - Estende Thread.
  - run(): ponto de entrada da thread.
  - Chama métodos da classe Buffer, que é protegido por
    semáforos e mutex.
 */

public class Producer extends Thread {

    private final Buffer buffer;
    private final String producerName;
    private static final int MAXIMUN_QUANTITY = 15; // máximo de 15 linhas

    public Producer(String producerName, Buffer buffer) {
        this.producerName = producerName;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= MAXIMUN_QUANTITY; i++) {
            try {
                // produz um item
                buffer.produce(producerName, i);

                // simulação do tempo de produção
                Thread.sleep(100);
            } catch (InterruptedException e) {
            	// tratamento de interrupção da thread
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
