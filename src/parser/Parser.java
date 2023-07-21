package parser;

import service.FiQ;
import service.Producer;
import service.Consumer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class Parser {
    private final String file_path = "fiq_store.txt";
    FiQ fiq = new FiQ(file_path);
    private InputCommandParser parser;
    static Producer producer;
    static Consumer consumer;
    private Method method;
    private BufferedReader bufferedReader;

    public Parser(){
        parser = new InputCommandParser();
        producer = new Producer(fiq);
        consumer = new Consumer(fiq,1);
    }

    /**
     * Function to be called in case of Interactive mode
     * @param text
     */
    public void parseText(String text){
        String[] textData = text.split(" ");

        if(textData.length < 1){
            // Blank input | Invalid Input
            System.out.println("Invalid Input");
        }
        else if(Objects.equals(textData[0], "produce_messages")){
            // start Producer
            try{
                method = parser.mapParser.get(textData[0]);
                if(method != null){
                    method.invoke(producer);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else if(Objects.equals(textData[0], "consume_messages")){
            // start consumer
            try{
                method = parser.mapParser.get(textData[0]);
                if(method != null){
                    method.invoke(consumer);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
