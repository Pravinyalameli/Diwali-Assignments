package Day5;

import java.util.Arrays;

public class Array {

	    public static void rotate(int[] nums, int k) {
	        int n = nums.length;
	        k = k % n; // handle k > n
	        int[] temp = new int[n];
	        
	        for (int i = 0; i < n; i++) {
	            temp[(i + k) % n] = nums[i];
	        }
	        
	       
	        for (int i = 0; i < n; i++) {
	            nums[i] = temp[i];
	        }
	    }

	    public static void main(String[] args) {
	        int[] nums = {1, 2, 3, 4, 5, 6, 7};
	        int k = 3;

	        System.out.println("Original Array:");
	        System.out.println(Arrays.toString(nums));

	        rotate(nums, k);

	        System.out.println("\nArray after rotating by " + k + " steps:");
	        System.out.println(Arrays.toString(nums));

	    }
	}