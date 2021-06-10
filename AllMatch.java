/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import java.util.stream.Stream;

// https://www.baeldung.com/java-streams-peek-api
// avoid using allMatch, try using anyMatch
public class AllMatch {
  public static void main(String[] args) {
    boolean result = Stream.of("doe", "res", "misdfdsf", "fadsfdsf", "sodsfdsf", "ladsfdsf", "tisdfdsf").filter(str -> str.length() > 5)
        .peek(System.out::println) // intermediate operation(lazy operation)
        .allMatch(str -> str.length() < 5); // terminal operation
    System.out.println(result);

    //peek for altering the inner state of an element
    Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
    userStream.peek(u -> u.setName(u.getName().toLowerCase())).forEach(System.out::println);// cant use map here
  }
}

//list of intermediate operations : filter(), map(), flatMap(), distinct(), sorted(), peek(), limit(), skip()
class User {
  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User(String name) {
    super();
    this.name = name;
  }

  @Override
  public String toString() {
    return "User [name=" + name + "]";
  }
}