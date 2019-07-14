package twoPointer;

import java.util.Arrays;

public class MaximumProductOfKNumbers {
	/**
	 *  给出一个数组，找出拿出K个数，product最大的 
	 *
	 */
	public static void main(String[] args) {
		int[] arr = {-1,6,8,-100};
		System.out.println(maximumProduct(arr,3));
	}
	
	
	//-1 6 8 9 100
	
	public static int maximumProduct(int[] nums, int k) {
      
        Arrays.sort(nums);
        int product = 1;		
        int indexLeft = 0;
        int indexRight = nums.length - 1;
        if(k % 2 != 0) {
            product = product * nums[indexRight];
            k--;
            indexRight--;  
        }
        //trick 在于如果K是奇数，就把最大的先拿出来
        //然后依次两个一组分别从最小端和最大端拿，因为两个负数product也是正的，所以两个一组拿就可以保证最大了
        
        while (k > 0) {
            if (nums[indexLeft] * nums[indexLeft + 1] >= nums[indexRight] * nums[indexRight - 1]) {
                product  = product * nums[indexLeft] * nums[indexLeft + 1];
                indexLeft += 2;
                k = k - 2;
            }
            else {
                product  = product * nums[indexRight] * nums[indexRight - 1];
                indexRight -= 2;
                k = k - 2;
            }

        }
        return product;
    }
}



