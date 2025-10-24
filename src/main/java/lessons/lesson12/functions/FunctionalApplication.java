package lessons.lesson12.functions;

import java.util.List;

public class FunctionalApplication {
    public static void main(String[] args) {
        FunctionalTasks functionalTasks = new FunctionalTasks();

        String testString = "TestString";

        // task 1
        System.out.println("строка не пуста и длиннее 3");
        System.out.println(functionalTasks.longerThenThree.test(testString));


        // task 2
        System.out.println("возвращающую длину строки.");
        System.out.println(functionalTasks.stringLen.apply(testString));


        // task 3
        System.out.println("возвращает новый UUID при каждом вызове.");
        System.out.printf("UUID 1: %s\n", functionalTasks.getUUID.get());
        System.out.printf("UUID 2: %s\n", functionalTasks.getUUID.get());


        // task 4
        System.out.println(" который выводит строку в upper case.");
        functionalTasks.toUpper.accept(testString);


        // task 5
        System.out.println("возвращает сумму двух чисел");
        System.out.println(functionalTasks.sum.apply(2, 3));


        // task 6
        String testForTrim = "    test";
        System.out.println("обрезает пробелы, потом делает верхний регистр.");
        System.out.println(functionalTasks.trimAndUpperCase.apply(testForTrim));


        // task 7
        System.out.println("печатает строку в консоль, второй — печатает длину строки");
        functionalTasks.printStringAndLen.accept(testString);


        // task 8
        Integer testForEven = -2;
        Integer testForPositive = 3;

        System.out.println("проверяет нечётное или отрицательное");
        System.out.println(functionalTasks.isEvenOrPositive.test(testForEven));
        System.out.println(functionalTasks.isEvenOrPositive.test(testForPositive));


        // task 9
        System.out.println("Multiply and to String");
        System.out.println(functionalTasks.multAndToStr.apply(3, 4));


        // task 10
        System.out.println("добавляет \"!!!\" к строке.");
        System.out.println(functionalTasks.scream.apply(testString));

        // task 11
        List<Integer> testNumList = List.of(3, 67, 4, 1, 5);


        System.out.println("вручную фильтрует коллекцию аналогично Stream API.");
        System.out.println(functionalTasks.filter(testNumList, (n) -> n >= 5));

        // task 12
        System.out.println("Custom map method");
        System.out.println(functionalTasks.map(testNumList, n -> n * n));

        // task 13
        functionalTasks.forEach(testNumList, System.out::println);

        // task 14
        System.out.println(functionalTasks.generate(() -> "item", 4));
    }
}
