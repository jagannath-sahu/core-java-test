
public class Equalize_Array { 
      
    // Returns minimum operations needed to 
    // equalize an array. 
    static int minOperations(int arr[], int n) 
    { 
        // Compute sum of array elements 
        int sum = 0; 
        for (int i = 0; i < n; i++) 
            sum += arr[i]; 
       
        // If average of array is not integer, 
        // then it is not possible to equalize 
        if (sum % n != 0) 
            return -1; 
       
        // Compute sum of absolute differences 
        // between array elements and average 
        // or equalized value 
        int diff_sum = 0; 
        int eq = sum / n; 
        for (int i = 0; i < n; i++) 
            diff_sum += Math.abs(arr[i] - eq); 
       
        return (diff_sum / 2); 
    } 
       
    // Driver code 
    public static void main(String args[]) 
    { 
        int arr[] = { 2, 2, 2, 0, 4 }; 
        int n = arr.length; 
        System.out.println(minOperations(arr, n)); 
    } 
} 