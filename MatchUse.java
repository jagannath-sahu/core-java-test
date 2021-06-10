/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MatchUse {
    public static void main(String []args) {
        IntPredicate positiveTemperature = temp -> temp > 0;   
        
        Supplier<IntStream> temperatureSupplier = () -> IntStream.of(-5, -6, -7, -5, 2, -8, -9);

        if(temperatureSupplier.get().anyMatch(positiveTemperature)) {       
             int temp = temperatureSupplier.get()
                              .filter(positiveTemperature)
                              .findAny()
                              .getAsInt();			  
		System.out.println(temp); 
        }
    }
}
