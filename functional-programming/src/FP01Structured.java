import java.util.List;

public class FP01Structured {
    public static void main(String[] args) {
        printAllNumbersInListStructured(List.of(2,12,43,22,12,54));
    }

    private static void printAllNumbersInListStructured(List<Integer> numbers) {
        for (int number: numbers) {
            System.out.println(number);
        }
    }

    private static void printEvenNumbersInListStructured(List<Integer> numbers) {
        for (int number: numbers) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
        }
    }
}
