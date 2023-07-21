package service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;


public class Consumer {
    private final FiQ fiq;
    private final int concurrencyFactor;
    private final ExecutorService executor;
    public String path = "fiq_store.txt";

    public Consumer(FiQ fiq, int concurrencyFactor) {
        this.fiq = fiq;
        this.concurrencyFactor = concurrencyFactor;
        this.executor = Executors.newFixedThreadPool(concurrencyFactor);
    }

    public void consumeMessages() throws IOException {
//        String message = null;
        while(true){
            executor.submit(() -> {
                String message;
                try {
                    message = fiq.getNextMessage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    processMessage(message);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void processMessage(String message) throws IOException, InterruptedException {
        String[] subMessage = message.split(":");
        String key  = subMessage[0];
        String msg = subMessage[1];
        String time = subMessage[2];
        System.out.println("Processing message with key: '" + key + "': '" + msg + "': '" + time + "'");
        fiq.deleteTextFromFile(path,message);
        Thread.sleep(Integer.parseInt(time) * 1000L);
        System.out.println("Processed message with key '" + key + "'");
    }
}
