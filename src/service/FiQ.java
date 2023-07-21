package service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class FiQ {
    private final String path;
    private final BlockingQueue<String> messageQueue;
    public FiQ(String path){
        this.path = path;
        this.messageQueue = new LinkedBlockingQueue<>();
    }
    public boolean writeMessage(String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
        writer.append(message).append("\n");
        writer.close();
        return true;
    }

    public String getNextMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String message = reader.readLine();
//        if (message == null){
//            return "no messages left";
//        }
        reader.close();
        return message;
    }
    public void deleteTextFromFile(String path, String message) throws IOException {
        Path filePath = Paths.get(path);
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = lines.stream()
                .filter(line -> !line.contains(message))
                .collect(Collectors.toList());
        Files.write(filePath,updatedLines);
    }
}
