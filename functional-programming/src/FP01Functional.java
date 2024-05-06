import java.util.List;

public class FP01Functional {
    public static void main(String[] args) {
        //printAllNumbers(List.of(2,12,43,22,12,54,101));
        //printEvenNumbers(List.of(34,4,2,6,23,54,34,567));
        //printOddNumbers(List.of(34,4,2,6,23,54,34,567));
        //printAllCourses(List.of("Python","Java","Docker","Javascript"));
        //printCoursesContainingRequiredWord(List.of("Python","Java","Docker","Javascript"), "Java");
        //printCoursesWithAtLeastGivenLength(List.of("Python","Java","Docker","Javascript"), 5);
        //printSquareOfEvenNumbers(List.of(2,12,43,22,12,54,101));
        //printCubeOfOddNumbers(List.of(2,12,43,22,12,54,11));
        printNumberOfCharactersInCourses(List.of("Python","Java","Docker","Javascript"));
    }

    private static void print(int number) {
        System.out.println(number);
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static void printAllNumbers(List<Integer> numbers) {
        //numbers.forEach(FP01Functional::print);
        numbers.forEach(System.out::println);
    }

    private static void printEvenNumbers(List<Integer> numbers) {
        //numbers.stream().filter(FP01Functional::isEven).forEach(System.out::println);
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
    }

    private static void printOddNumbers(List<Integer> numbers) {
        numbers.stream().filter(n -> n % 2 == 1).forEach(System.out::println);
    }

    private static void printAllCourses(List<String> courses) {
        courses.forEach(System.out::println);
    }

    private static void printCoursesContainingRequiredWord(List<String> courses, String filter) {
        courses.stream().filter(c -> c.contains(filter)).forEach(System.out::println);
    }

    private static void printCoursesWithAtLeastGivenLength(List<String> courses, Integer length) {
        courses.stream().filter(c -> c.length() >= length).forEach(System.out::println);
    }

    private static void printSquareOfEvenNumbers(List<Integer> numbers) {
        numbers.stream().filter(n -> n % 2 == 0).map(n -> n * n).forEach(System.out::println);
    }

    private static void printCubeOfOddNumbers(List<Integer> numbers) {
        numbers.stream().filter(n -> n % 2 == 1).map(n -> n * n * n).forEach(System.out::println);
    }

    private static void printNumberOfCharactersInCourses(List<String> courses) {
        courses.stream().map(String::length).forEach(System.out::println);
    }
}
