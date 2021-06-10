import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class BiFunctionTest {

  public static void main(String[] args) {

    String result = Stream.of("hello", "world", "complete").reduce("", (a, b) -> combineWithoutTrailingDash(a, b));

    System.out.println(result); // complete-world-hello

    List<String> list1 = Arrays.asList("a", "b", "c");
    List<Integer> list2 = Arrays.asList(1, 2, 3);

    List<String> result1 = listCombiner(list1, list2, (a, b) -> a + b);

    System.out.println(result1); // [a1, b2, c3]

    List<Double> list3 = Arrays.asList(1.0d, 2.1d, 3.3d);
    List<Float> list4 = Arrays.asList(0.1f, 0.2f, 4f);

    List<Boolean> result2 = listCombiner(list3, list4, (a, b) -> a > b);
    System.out.println(result2); // [true, true, false]

    List<Boolean> result3 = listCombiner(list3, list4, BiFunctionTest::firstIsGreaterThanSecond); // A BiFunction Method
                                                                                                  // Reference
    System.out.println(result3); // [true, true, false]

    List<Float> list5 = Arrays.asList(0.1f, 0.2f, 4f);
    List<Float> list6 = Arrays.asList(0.1f, 0.2f, 4f);


    List<Boolean> result4 = listCombiner(list5, list6, Float::equals);

    System.out.println(result4); // [true, true, true]

    List<Float> result5 = listCombiner(list5, list6, Float::sum);

    System.out.println(result5); // [0.2, 0.4, 8.0]

    List<Float> result6 = listCombiner(list5, list6, Float::max);

    System.out.println(result6); // [0.1, 0.2, 4.0]

    List<Double> list7 = Arrays.asList(1.0d, 2.1d, 3.3d);
    List<Double> list8 = Arrays.asList(0.1d, 0.2d, 4d);

    List<Boolean> result7 = listCombiner(list7, list8, asBiFunction(Double::compareTo).andThen(i -> i > 0)); // Composing BiFunctions

    System.out.println(result7); // [true, true, false]
  }

  private static String combineWithoutTrailingDash(String a, String b) {
    if (a.isEmpty()) {
      return b;
    }
    return b + "-" + a;
  }

  private static <T, U, R> List<R> listCombiner(List<T> list1, List<U> list2, BiFunction<T, U, R> combiner) {
    List<R> result = new ArrayList<>();
    for (int i = 0; i < list1.size(); i++) {
      result.add(combiner.apply(list1.get(i), list2.get(i)));
    }
    return result;
  }

  private static boolean firstIsGreaterThanSecond(Double a, Float b) {
    return a > b;
  }

  private static <T, U, R> BiFunction<T, U, R> asBiFunction(BiFunction<T, U, R> function) {
    return function;
  }

}
