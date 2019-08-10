package analyze;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Analizy {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unavailable(String source, String target) {
        List<String> listOfInterval = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.matches("^[45]00\\s.*")) {
                    StringBuilder interval = new
                            StringBuilder(currentLine.split("\\s")[1]).append(';');
                    while ((currentLine = reader.readLine()) != null) {
                        if (currentLine.matches("^[23]00\\s.*")) {
                            interval.append(currentLine.split("\\s")[1]).append('\n');
                            listOfInterval.add(interval.toString());
                            break;
                        }
                    }
                }
            }
            saveIntervalsToFile(listOfInterval, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveIntervalsToFile(List<String> interval, String target) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            interval.stream().forEach(writer::write);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
