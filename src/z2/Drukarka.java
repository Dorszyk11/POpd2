import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Drukarka {

    public static void main(String[] args) {
        try {
            String inputFile = "dane1.txt";
            String outputFile = "wynik.txt";

            PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("koniec")) {
                        break;
                    }
                    processLine(line, queue, writer);
                }
                while (!queue.isEmpty()) {
                    int number = queue.poll();
                    writer.write(number + "\n");
                }
            } catch (IOException e) {
                System.err.println("Błąd operacji wejścia/wyjścia: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Nieoczekiwany błąd: " + e.getMessage());
        }
    }

    private static void processLine(String line, PriorityQueue<Integer> queue, BufferedWriter writer) throws IOException {
        if (line.startsWith("{")) {
            int number = Integer.parseInt(line.substring(1, line.length() - 1));
            queue.add(number);
        } else if (line.equals("drukuj")) {
            if (!queue.isEmpty()) {
                int highestPriorityNumber = queue.poll();
                writer.write(highestPriorityNumber + "\n");
            } else {
                writer.write("brak\n");
            }
        }
    }
}
