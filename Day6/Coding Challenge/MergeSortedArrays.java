package Day6;

import java.util.Arrays;

public class MergeSortedArrays {
    
    public static int[] merge(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] result = new int[n + m];
        
        int i = 0, j = 0, k = 0;
        
        // Merge both arrays while both have elements
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        
        // Copy remaining elements from nums1 (if any)
        while (i < n) {
            result[k++] = nums1[i++];
        }
        
        // Copy remaining elements from nums2 (if any)
        while (j < m) {
            result[k++] = nums2[j++];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {1, 3, 5, 7};
        int[] nums2 = {2, 4, 6, 8};
        int[] result = merge(nums1, nums2);
        System.out.println("Merged array: " + Arrays.toString(result));
        // Output: [1, 2, 3, 4, 5, 6, 7, 8]
        
        // Edge case: one empty array
        int[] nums3 = {};
        int[] nums4 = {1, 2, 3};
        System.out.println("Empty + Non-empty: " + Arrays.toString(merge(nums3, nums4)));
        // Output: [1, 2, 3]
        
        // Edge case: both empty
        int[] nums5 = {};
        int[] nums6 = {};
        System.out.println("Both empty: " + Arrays.toString(merge(nums5, nums6)));
        // Output: []
        
        // Edge case: with duplicates
        int[] nums7 = {1, 2, 2, 3};
        int[] nums8 = {2, 4, 4, 5};
        System.out.println("With duplicates: " + Arrays.toString(merge(nums7, nums8)));
        // Output: [1, 2, 2, 2, 3, 4, 4, 5]
    }
}