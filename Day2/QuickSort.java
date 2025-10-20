package Day2;

import java.util.Arrays;

public class QuickSort {

    /**
     * Public method to sort an array of strings using QuickSort
     * @param arr the array to be sorted
     */
    public static void sort(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive QuickSort implementation
     * @param arr the array to be sorted
     * @param low the starting index
     * @param high the ending index
     */
    private static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partition method that selects last element as pivot
     * @param arr the array to be partitioned
     * @param low the starting index
     * @param high the ending index
     * @return the pivot index
     */
    private static int partition(String[] arr, int low, int high) {
        // Select the rightmost element as pivot
        String pivot = arr[high];
        
        // Index of smaller element (indicates right position of pivot)
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        
        // Place pivot in correct position
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Alternative partition method using first element as pivot
     */
    private static int partitionFirst(String[] arr, int low, int high) {
        String pivot = arr[low];
        int i = low + 1;
        int j = high;
        
        while (i <= j) {
            while (i <= high && arr[i].compareTo(pivot) <= 0) {
                i++;
            }
            while (j > low && arr[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        
        swap(arr, low, j);
        return j;
    }

    /**
     * Randomized partition to avoid worst-case scenario
     */
    private static int randomizedPartition(String[] arr, int low, int high) {
        // Randomly select pivot between low and high
        int randomIndex = low + (int) (Math.random() * (high - low + 1));
        swap(arr, randomIndex, high);
        return partition(arr, low, high);
    }

    /**
     * Three-way quicksort for handling duplicate values efficiently
     */
    public static void threeWayQuickSort(String[] arr, int low, int high) {
        if (low >= high) return;
        
        String pivot = arr[low];
        int lt = low;      // arr[low..lt-1] < pivot
        int gt = high;     // arr[gt+1..high] > pivot
        int i = low + 1;   // arr[lt..i-1] == pivot
        
        while (i <= gt) {
            int cmp = arr[i].compareTo(pivot);
            if (cmp < 0) {
                swap(arr, lt++, i++);
            } else if (cmp > 0) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        
        threeWayQuickSort(arr, low, lt - 1);
        threeWayQuickSort(arr, gt + 1, high);
    }

    /**
     * Swap two elements in the array
     */
    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Case-insensitive QuickSort
     */
    public static void sortCaseInsensitive(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortCaseInsensitive(arr, 0, arr.length - 1);
    }

    private static void quickSortCaseInsensitive(String[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionCaseInsensitive(arr, low, high);
            quickSortCaseInsensitive(arr, low, pivotIndex - 1);
            quickSortCaseInsensitive(arr, pivotIndex + 1, high);
        }
    }

    private static int partitionCaseInsensitive(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j].compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Utility method to print array
     */
    public static void printArray(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Check if array is sorted
     */
    public static boolean isSorted(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if array is sorted (case-insensitive)
     */
    public static boolean isSortedCaseInsensitive(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareToIgnoreCase(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    // Test method
    public static void main(String[] args) {
        System.out.println("=== Testing QuickSort for Strings ===\n");

        // Test 1: Basic sorting
        System.out.println("Test 1: Basic Sorting");
        String[] arr1 = {"banana", "apple", "cherry", "date", "blueberry"};
        System.out.print("Original: ");
        printArray(arr1);
        sort(arr1);
        System.out.print("Sorted:   ");
        printArray(arr1);
        System.out.println("Is sorted: " + isSorted(arr1));
        System.out.println();

        // Test 2: Empty array
        System.out.println("Test 2: Empty Array");
        String[] arr2 = {};
        System.out.print("Original: ");
        printArray(arr2);
        sort(arr2);
        System.out.print("Sorted:   ");
        printArray(arr2);
        System.out.println();

        // Test 3: Single element
        System.out.println("Test 3: Single Element");
        String[] arr3 = {"hello"};
        System.out.print("Original: ");
        printArray(arr3);
        sort(arr3);
        System.out.print("Sorted:   ");
        printArray(arr3);
        System.out.println("Is sorted: " + isSorted(arr3));
        System.out.println();

        // Test 4: Already sorted array
        System.out.println("Test 4: Already Sorted");
        String[] arr4 = {"apple", "banana", "cherry", "date"};
        System.out.print("Original: ");
        printArray(arr4);
        sort(arr4);
        System.out.print("Sorted:   ");
        printArray(arr4);
        System.out.println("Is sorted: " + isSorted(arr4));
        System.out.println();

        // Test 5: Reverse sorted array
        System.out.println("Test 5: Reverse Sorted");
        String[] arr5 = {"date", "cherry", "banana", "apple"};
        System.out.print("Original: ");
        printArray(arr5);
        sort(arr5);
        System.out.print("Sorted:   ");
        printArray(arr5);
        System.out.println("Is sorted: " + isSorted(arr5));
        System.out.println();

        // Test 6: Array with duplicates
        System.out.println("Test 6: With Duplicates");
        String[] arr6 = {"banana", "apple", "cherry", "apple", "date", "banana"};
        System.out.print("Original: ");
        printArray(arr6);
        sort(arr6);
        System.out.print("Sorted:   ");
        printArray(arr6);
        System.out.println("Is sorted: " + isSorted(arr6));
        System.out.println();

        // Test 7: Case-sensitive sorting
        System.out.println("Test 7: Case-Sensitive Sorting");
        String[] arr7 = {"Apple", "banana", "Cherry", "apple", "cherry"};
        System.out.print("Original: ");
        printArray(arr7);
        sort(arr7);
        System.out.print("Sorted:   ");
        printArray(arr7);
        System.out.println("Is sorted: " + isSorted(arr7));
        System.out.println();

        // Test 8: Case-insensitive sorting
        System.out.println("Test 8: Case-Insensitive Sorting");
        String[] arr8 = {"Apple", "banana", "Cherry", "apple", "cherry"};
        System.out.print("Original: ");
        printArray(arr8);
        sortCaseInsensitive(arr8);
        System.out.print("Sorted:   ");
        printArray(arr8);
        System.out.println("Is sorted (case-insensitive): " + isSortedCaseInsensitive(arr8));
        System.out.println();

        // Test 9: Three-way quicksort for duplicates
        System.out.println("Test 9: Three-Way QuickSort for Duplicates");
        String[] arr9 = {"banana", "apple", "cherry", "apple", "date", "banana", "apple"};
        System.out.print("Original: ");
        printArray(arr9);
        threeWayQuickSort(arr9, 0, arr9.length - 1);
        System.out.print("Sorted:   ");
        printArray(arr9);
        System.out.println("Is sorted: " + isSorted(arr9));
        System.out.println();

        // Test 10: Large array
        System.out.println("Test 10: Large Array");
        String[] arr10 = {
            "zebra", "apple", "mango", "banana", "cherry", 
            "date", "elderberry", "fig", "grape", "honeydew"
        };
        System.out.print("Original: ");
        printArray(arr10);
        sort(arr10);
        System.out.print("Sorted:   ");
        printArray(arr10);
        System.out.println("Is sorted: " + isSorted(arr10));
    }
}