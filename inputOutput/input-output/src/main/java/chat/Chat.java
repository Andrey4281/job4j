package chat;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Chat {
    private String inputFileName;
    private String outputFileName;
    private List<String> randomPhrases;
    private boolean isAnswer;

    public Chat(final String inputFileName, final String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.randomPhrases = new ArrayList<>();
        this.isAnswer = true;
    }

    public static void main(String[] args) {
        Chat chat = new Chat("phrases", "dialogue");
        chat.setRandomPhrases();
        chat.beginChat();
    }

    public void setRandomPhrases() {
        try (Scanner input = new Scanner(new File(inputFileName))) {
            while (input.hasNextLine()) {
                randomPhrases.add(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void beginChat() {
        Scanner in = new Scanner(System.in);
        String message;
        String answer;
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            do {
                message = in.nextLine();
                writer.write(message);
                writer.write('\n');
                if (message.equals("закончить") || message.equals("стоп")) {
                    isAnswer = false;
                } else if (message.equals("продолжить")) {
                    isAnswer = true;
                }
                getAnswer(writer);
            } while (!message.equals("закончить"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAnswer(PrintWriter writer) {
        if (isAnswer) {
            String answer = randomPhrases.get((int) (Math.random()
                    * (randomPhrases.size() - 1)));
            System.out.println(answer);
            writer.write(answer);
            writer.write('\n');
        }
    }
}
