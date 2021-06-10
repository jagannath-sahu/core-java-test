/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class SortByLength {
  public static void main(String[] args) {
    List<String> words = Arrays.asList("follow your heart but take your brain with you".split(" "));
    Comparator<String> lengthCompare = (str1, str2) -> str1.length() - str2.length();
    words = words.stream().distinct().sorted(lengthCompare).collect(Collectors.toList());
    System.out.println(words);
    
    String temp = "0.75";
    double temp1 = Double.parseDouble(temp);
    double temp2 = temp1 * 5;
    double x = 1.3;
    int y = (int)x;
    System.out.println(y);
  }
}
