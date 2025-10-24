package lessons.lesson12.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionalTasks {
    public Predicate<String> longerThenThree = s -> s != null && s.length() > 3;

    public Function<String, Integer> stringLen = String::length;

    public Supplier<UUID> getUUID = UUID::randomUUID;

    public Consumer<String> toUpper = s -> System.out.println(s.toUpperCase());

    public BiFunction<Integer, Integer, Integer> sum = Integer::sum;

    private final Function<String, String> trim = String::trim;
    private final Function<String, String> upper = String::toUpperCase;
    public Function<String, String> trimAndUpperCase = trim.andThen(upper);


    private final Consumer<String> printString = System.out::println;
    private final Consumer<String> printLen = s -> System.out.println(s.length());
    public Consumer<String> printStringAndLen = printString.andThen(printLen);


    public Predicate<Integer> isEven = n -> n % 2 == 0;
    public Predicate<Integer> isPositive = n -> n > 0;
    public Predicate<Integer>  isEvenOrPositive = isEven.or(isPositive);


    private final BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
    private final Function<Integer, String> toStr = x -> "Result: " + x;
    public BiFunction<Integer, Integer, String> multAndToStr = multiply.andThen(toStr);

    public UnaryOperator<String> scream = s -> s + "!!!";


    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> output = new ArrayList<>();

        for (T t : list) {
            if (predicate.test(t)) {
                output.add(t);
            }
        }

        return output;
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> output = new ArrayList<>();

        for (T t : list) {
            output.add(mapper.apply(t));
        }

        return output;
    }

    public <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) consumer.accept(t);
    }

    public <T> List<T> generate(Supplier<T> supplier, int n) {
        List<T> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            result.add(supplier.get());
        }

        return result;
    }
}
