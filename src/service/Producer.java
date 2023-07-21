package service;

import java.io.IOException;
import java.time.Instant;
import java.util.Scanner;

public class Producer {
    private final FiQ fiq;
    public Producer(FiQ fiq){
        this.fiq = fiq;
    }
    public void produceMessages() throws IOException {
        String[] messages;
        boolean success = false;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Please enter the message");
            String message = sc.next();
            success = fiq.writeMessage(message);
            if (success == true){
                System.out.println("Successfully written the messages to the file.");
            }
        }
//        String[] messages = {
//                "key1:Message 1:"+ Instant.now(),
//                "key2:Message 2:"+ Instant.now(),
//                "key3:Message 3:"+ Instant.now(),
//                "key4:Message 4:"+ Instant.now(),
//                "key5:Message 5:"+ Instant.now(),
//                "key6:Message 6:"+ Instant.now(),
//                "key7:Message 7:"+ Instant.now(),
//                "key8:Message 8:"+ Instant.now(),
//                "key9:Message 9:"+ Instant.now(),
//                "key10:Message 10:"+ Instant.now(),
//        };
//        for (String message : messages){
//            success = fiq.writeMessage(message);
//        }

    }
}
