import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import parser.Parser;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        BufferedReader bufferedReader;
        String inputText;

        switch (args.length) {
            case 0 -> {

                // Interactive mode
                System.out.println("Enter 'exit' to quit");
                while (true) {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                        inputText = bufferedReader.readLine();
                        if (inputText.equalsIgnoreCase("exit")) {
                            break;
                        } else if (inputText.trim().length() == 0 || inputText == null) {
                            continue;
                        } else {
                            parser.parseText(inputText.trim());
                        }
                    } catch (IOException e) {
                        System.out.println("Some issue in input string");
                        e.printStackTrace();
                    }
                }
            }
            default -> System.out.println("Invalid input");
        }
    }
}