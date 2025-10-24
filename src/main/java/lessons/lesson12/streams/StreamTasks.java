package lessons.lesson12.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasks {
    // task 1
    public List<Integer> filterEvenNumbersStream(final List<Integer> input) {
        return input.stream().filter(n -> n % 2 == 0).map(a -> a * a).toList();
    }

    public List<Integer> filterEvenNumbersLoop(final List<Integer> input) {
        List<Integer> output = new ArrayList<>();

        for (Integer n : input) {
            if (n % 2 == 0) {
                output.add(n * n);
            }
        }

        return output;
    }


    // task 2
    public int countWordsLongerThenFiveStream(final List<String> input) {
        return input.stream().filter(s -> s.length() > 5).toList().size();
    }

    public int countWordsLongerThenFiveLoop(final List<String> input) {
        int count = 0;

        for (String s : input) {
            if (s.length() > 5) count++;
        }

        return count;
    }


    // task 3
    public int findMaxStream(final List<Integer> input) {
        if (input.isEmpty()) return 0;

        return input.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    public int findMinStream(final List<Integer> input) {
        if (input.isEmpty()) return 0;

        return input.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    public int findMaxLoop(final List<Integer> input) {
        if (input.isEmpty()) return 0;

        int max = 0;

        for (Integer n : input) {
            if (n > max) max = n;
        }

        return max;
    }

    public int findMinLoop(final List<Integer> input) {
        if (input.isEmpty()) return 0;

        int min = input.get(0);

        for (Integer n : input) {
            if (n < min) min = n;
        }

        return min;
    }


    // task 4
    public int avgStringLenStream(final List<String> input) {
        return input.stream().map(String::length).reduce(0, Integer::sum) / input.size();
    }

    public int avgStringLenLoop(final List<String> input) {
        int sum = 0;

        for (String s : input) {
            sum += s.length();
        }

        return sum / input.size();
    }

    // task 5
    public List<String> sortAndRemoveDupesStream(final List<String> input) {
        return input.stream().distinct().sorted(Comparator.comparingInt(String::length)).toList();
    }


    public List<String> sortAndRemoveDupesLoop(final List<String> input) {
        List<String> output = new ArrayList<>();

        for (String s : input) {
            if (!output.contains(s)) {
                output.add(s);
            }
        }

        for (int i = 0; i < output.size() - 1; i++) {
            for (int l = 0; l < output.size() - i - 1; l++) {
                String firstString = output.get(l);
                String secondString = output.get(l + 1);

                if (firstString.length() > secondString.length()) {
                    Collections.swap(output, l, l + 1);
                }
            }
        }

        return output;
    }


    // task 6
    public Map<String, Integer> mapStringListToMapStream(final List<String> input) {
        return input.stream().collect(Collectors.toMap(s -> s, String::length));
    }

    public Map<String, Integer> mapStringListToMapLoop(final List<String> input) {
        Map<String, Integer> output = new HashMap<>();

        for (String s : input) output.put(s, s.length());

        return output;
    }


    // task 7
    public Map<Character, List<String>> groupByFirstLetterStream(final List<String> input) {
        return input.stream().collect(Collectors.groupingBy(name -> name.charAt(0)));
    }

    public Map<Character, List<String>> groupByFirstLetterLoop(final List<String> input) {
        Map<Character, List<String>> output = new HashMap<>();

        for (String s : input) {
            List<String> words = output.getOrDefault(s.charAt(0), new ArrayList<>());
            words.add(s);
            output.put(s.charAt(0), words);
        }

        return output;
    }

    // task 8
    public String joinListStream(final List<String> input) {
        return input.stream().collect(Collectors.joining(", "));
    }

    public String joinList(final List<String> input) {
        return String.join(", ", input);
    }

    // task 9
    public List<String> splitStringsStream(final List<String> input) {
        return input.stream().flatMap(s -> Stream.of(s.split(" "))).toList();
    }


    public List<String> splitStringsLoop(final List<String> input) {
        List<String> output = new ArrayList<>();

        for (String s : input) {
            output.addAll(Arrays.asList(s.split(" ")));
        }

        return output;
    }

    // task 10
    public Map<String, Product> findMostExpensiveInCategoryStream(final List<Product> input) {
        return input.stream().collect(Collectors.toMap(Product::category, p -> p, (p1, p2) -> p1.price() > p2.price() ? p1 : p2));
    }


    public Map<String, Product> findMostExpensiveInCategoryLoop(final List<Product> input) {
        Map<String, Product> output = new HashMap<>();

        for (Product product : input) {
            Product maxPriceProduct = output.get(product.category());
            double categoryMaxPrice = 0;
            if (maxPriceProduct != null) categoryMaxPrice = maxPriceProduct.price();

            if (product.price() > categoryMaxPrice) {
                output.put(product.category(), product);
            }
        }

        return output;
    }
}
