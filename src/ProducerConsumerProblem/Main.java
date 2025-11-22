package ProducerConsumerProblem;

/**
  Classe Principal
 
  - Criação dos recursos compartilhados (Buffer) e as threads.
  - Demonstra a criação, inicialização e sincronização (join) de threads.
  - Fecha o arquivo de log.
 */

public class Main {

	public static void main(String[] args) {
		try {
            // cria o buffer com 7 posições e o arquivo de log de saída
            Buffer buffer = new Buffer(7, "log_buffer.txt");
            
            // cria as threads produtor e consumidor
            Producer producer = new Producer("Produtor-1", buffer);
            Consumer consumer = new Consumer("Consumidor-1", buffer);

            // inicialização
            producer.start();
            consumer.start();

            // aguarda a conclusão das threads 
            producer.join();
            consumer.join();

            // fecha o arquivo do logo
            buffer.logClose();

            System.out.println("Execução concluída. Verifique o arquivo log_buffer.txt.");

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
