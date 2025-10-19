package Arrays;
import java.util.Arrays;

public class LongestStringLength {
    
    public static int longestStringLength(String[] strings) {
        if (strings == null || strings.length == 0) {
            return 0;
        }
        
        int maxLength = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] != null && strings[i].length() > maxLength) {
                maxLength = strings[i].length();
            }
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
        String[][] testArrays = {
            {"apple", "banana", "cherry"},
            {"short", "verylongword", "medium"},
            {"a", "bb", "ccc", "dddd"},
            {},
            {"single"},
            {null, "test", "hello"},
            null
        };
        
        for (String[] arr : testArrays) {
            int result = longestStringLength(arr);
            System.out.println("Array: " + Arrays.toString(arr) + 
                             " -> Longest length: " + result);
        }
    }
}