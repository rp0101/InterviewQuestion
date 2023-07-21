package parser;

import service.Consumer;
import service.Producer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InputCommandParser {

    public Map<String, Method> mapParser;

    public InputCommandParser(){
        mapParser = new HashMap<String, Method>();
        try{
            populateDataInMap();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }

    /**
     * Populate all possible commands in map
     * @throws NoSuchMethodException
     */
    private void populateDataInMap() throws NoSuchMethodException {
        mapParser.put("produce_messages", Producer.class.getMethod("produceMessages"));
        mapParser.put("consume_messages", Consumer.class.getMethod("consumeMessages"));
    }
}
