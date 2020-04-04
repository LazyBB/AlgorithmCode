package com.company.DivideAndConquer;

public class nixuPairs {
    public int reversePairs(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return reversePairs(nums, left, right);
    }

    private int reversePairs(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int rightPairs = reversePairs(nums, left, mid);
        int leftPairs = reversePairs(nums, mid + 1, right);
        if (nums[mid] <= nums[mid + 1]) {
            return rightPairs + leftPairs;
        }
        int crossPairs = crossPairs(nums, left, mid, right);
        return crossPairs + rightPairs + leftPairs;

    }

    private int crossPairs(int[] nums, int left, int mid, int right) {
        int temp[] = new int[right - left + 1];
        int count = 0;
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                count += ((mid - i) + 1);
            }
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        k=0;
        while (left<=right){
            nums[left++]=temp[k++];
        }
        return count;
    }
}
