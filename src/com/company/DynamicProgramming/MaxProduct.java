package com.company.DynamicProgramming;

/*
    给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    基础思路 同53题，dp[i]:以num[i]结尾的乘积,若不考虑正负问题
    公式：max= max（dp[i]*num[i+1],num[i+1])
    难点在正负号的处理
* */

public class MaxProduct {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int minPre = 1, maxPre = 1;
        int max ;
        int min ;
        int MaxP = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num >= 0) {
                max=Math.max(maxPre*num,num);
                min=Math.min(minPre,num);
            }else {
                max=Math.max(minPre*num,num);
                min=Math.min(maxPre*num,num);

            }
            MaxP=Math.max(MaxP,max);
            minPre=min;
            maxPre=max;
        }
        return MaxP;
    }
}
