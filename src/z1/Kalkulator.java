import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Kalkulator {
    public static int calculate(String line) throws ArithmeticException {
        String[] elements = line.split("\\s+");
        int num1 = Integer.parseInt(elements[0]);
        int num2 = Integer.parseInt(elements[2]);
        char operator = elements[1].charAt(0);

        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Dzielenie przez zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Nieprawidlowe dzialanie: " + operator);
        }
    }

    public static void main(String[] args) {
        String inputFileName = "dane1.txt";
        String outputFileName = "wynik.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int result = calculate(line);
                    writer.write(Integer.toString(result));
                    writer.newLine();
                } catch (ArithmeticException e) {
                    writer.write(e.getMessage());
                    writer.newLine();
                } catch (Exception e) {
                    System.out.println("Wystapil blad podczas przetwarzania danych: " + e.getMessage());
                }
            }

            System.out.println("Obliczenia zakonczone. Wyniki zapisane w pliku " + outputFileName);

        } catch (IOException e) {
            System.out.println("Wystapil blad podczas operacji na plikach: " + e.getMessage());
        }
    }
}
