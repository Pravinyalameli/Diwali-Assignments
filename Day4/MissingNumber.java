package Day4;

import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements (distinct, from 0 to n):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Using sum formula
        int totalSum = n * (n + 1) / 2;  // Sum of 0 to n
        int arrSum = 0;
        for (int num : arr) {
            arrSum += num;
        }

        int missingNumber = totalSum - arrSum;

        System.out.println("Missing number is: " + missingNumber);
        sc.close();
    }
}