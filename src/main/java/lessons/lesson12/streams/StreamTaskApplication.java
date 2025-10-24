package lessons.lesson12.streams;

import java.util.List;

public class StreamTaskApplication {
    public static void main(String[] args) {
        StreamTasks streamTasks = new StreamTasks();

        // Task 1
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        System.out.println("Task 1 - Оставь только чётные и выведи их квадраты.");
        System.out.println("Stream: " + streamTasks.filterEvenNumbersStream(numbers));
        System.out.println("Loop: " + streamTasks.filterEvenNumbersLoop(numbers));
        System.out.println("=================");

        // Task 2
        List<String> words = List.of("apple", "banana", "pear", "pineapple");

        System.out.println("Task 2 - Подсчитай, сколько строк в списке длиннее 5 символов.");
        System.out.println("Stream: " + streamTasks.countWordsLongerThenFiveStream(words));
        System.out.println("Loop: " + streamTasks.countWordsLongerThenFiveLoop(words));
        System.out.println("=================");

        // task 3
        System.out.println("Task 3 - максимальное и минимальное число в списке");
        System.out.println("Max Stream: " + streamTasks.findMaxStream(numbers));
        System.out.println("Max Loop: " + streamTasks.findMaxLoop(numbers));

        System.out.println("Min Stream: " + streamTasks.findMinStream(numbers));
        System.out.println("Min Loop: " + streamTasks.findMinLoop(numbers));
        System.out.println("=================");


        // task 4
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        System.out.println("Task 4 - Посчитай среднюю длину строк в списке");
        System.out.println("Stream: " + streamTasks.avgStringLenStream(names));
        System.out.println("Loop: " + streamTasks.avgStringLenLoop(names));
        System.out.println("=================");

        // task 5
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");

        System.out.println("Task 5 - Удали дубликаты и отсортируй");
        System.out.println("Stream: " + streamTasks.sortAndRemoveDupesStream(input));
        System.out.println("Loop: " + streamTasks.sortAndRemoveDupesLoop(input));
        System.out.println("=================");

        // task 6
        List<String> fruits = List.of("apple", "banana", "kiwi");

        System.out.println("Task 6 - Преобразуй список строк в Map");
        System.out.println("Stream: " + streamTasks.mapStringListToMapStream(fruits));
        System.out.println("Loop: " + streamTasks.mapStringListToMapLoop(fruits));
        System.out.println("=================");

        // task 7
        List<String> names2 = List.of("Alice", "Andrew", "Bob", "Charlie", "Catherine");

        System.out.println("Task 7 - Сгруппируй имена по первой букве.\n");
        System.out.println("Stream: " + streamTasks.groupByFirstLetterStream(names2));
        System.out.println("Loop: " + streamTasks.groupByFirstLetterLoop(names2));
        System.out.println("=================");

        // Task 8
        List<String> names3 = List.of("Tom", "Jerry", "Spike");

        System.out.println("Task 8 - Собери список имён в одну строку через запятую");
        System.out.println("Stream: " + streamTasks.joinListStream(names3));
        System.out.println("Loop: " + streamTasks.joinList(names3));
        System.out.println("=================");

        // Task 9
        List<String> sentences = List.of("Java is cool", "Streams are powerful");

        System.out.println("Task 9 - Из списка предложений получить список всех слов.");
        System.out.println("Stream: " + streamTasks.splitStringsStream(sentences));
        System.out.println("Loop: " + streamTasks.splitStringsLoop(sentences));
        System.out.println("=================");

        // task 10
        List<Product> products = List.of(
            new Product("Phone", "Electronics", 1200),
            new Product("TV", "Electronics", 1800),
            new Product("Apple", "Fruits", 2.5),
            new Product("Mango", "Fruits", 4.0));


        System.out.println("task 10 - Найди самый дорогой продукт в каждой категории.");

        System.out.println("Stream: " + streamTasks.findMostExpensiveInCategoryStream(products));
        System.out.println("Loop: " + streamTasks.findMostExpensiveInCategoryLoop(products));
    }
}
