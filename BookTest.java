package java8.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookTest {

  public static void main(String[] args) {
    List<Book> bookList = new ArrayList<>();
    bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
    //bookList.add(new Book("The Two Towers", 1954, "0345339711"));
    bookList.add(new Book("The Return of the King", 1955, "0618129111"));

    System.out.println(bookList.stream().collect(Collectors.toMap(Book::getReleaseYear, valueMapper -> valueMapper)));

    System.out.println("+++++++++++++++++++++++++++++++++++++");

    List<String> values = Arrays.asList("abc", "ab", "bc", "bcd", "a"); // I group by length and put it into a TreeMap then get the max value
    values.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList())).lastEntry().getValue().forEach(System.out::println);
    System.out.println(values.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList())) .lastEntry().getKey());
    System.out.println(values.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList())).lastEntry());

    String input = "Thequickbrownfoxjumps";
    final AtomicInteger atomicInteger = new AtomicInteger(0);
    Collection<String> result = input.chars()
                                        .mapToObj(c -> String.valueOf((char)c) )
                                        .collect(Collectors.groupingBy(c -> atomicInteger.getAndIncrement() / 4
                                                                    ,Collectors.joining()))
                                        .values();
    System.out.println(result);

    Object[][] ab = Stream.of("doe", "res", "misdfdsf", "fadsfdsf", "sodsfdsf", "ladsfdsf", "tisdfdsf").map(s -> new Object[] { s }).collect(Collectors.toList()).toArray(new Object[0][]);
    System.out.println(ab[0]);
    System.out.println(ab[0][0]);

    System.out.println(ab[1][0]);
    System.out.println(ab[2][0]);
    System.out.println(ab[3][0]);

    Stream.of("d2", "a2", "b1", "b3", "c")
    .sorted((s1, s2) -> {
        System.out.printf("sort: %s; %s\n", s1, s2);
        return s1.compareTo(s2);
    })
    .filter(s -> {
        System.out.println("filter: " + s);
        return s.startsWith("a");
    })
    .map(s -> {
        System.out.println("map: " + s);
        return s.toUpperCase();
    })
    .forEach(s -> System.out.println("forEach: " + s));

    Calculator calculator = new Calculator();
    String result1 = calculator.calc((a, b) -> ": " + (a * b),3,  5);

    System.out.println(result1);

    Map<Integer, String> map = new HashMap<Integer, String>();

    // Mapping string values to int keys
    map.put(10, "Geeks");
    map.put(15, "4");
    map.put(20, "Geeks");
    map.put(25, "Welcomes");
    map.put(30, "You");

    // Displaying the Map
    System.out.println("Initial Mappings are: " + map);

    // Clearing the map using clear()
    map.clear();

    // Displaying the final HashMap
    System.out.println("Finally the maps look like this: " + map);
    System.out.println("map------:"+map.get(10));
    System.out.println("ghfasjdfajdh");
  }
}

class Calculator {
  public String calc(BiFunction<Integer, Integer, String> bi, Integer i1, Integer i2) {
      return bi.apply(i1, i2);
  }
}
