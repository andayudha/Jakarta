/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 7/20/2016.
 */
public class BurstingBalloon {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("balloons.txt"));
            int tc = sc.nextInt();
            for(int t=1;t<=tc;t++){
                int n = sc.nextInt();
                int[] nums = new int[n];
                for(int i=0;i<n;i++){
                    nums[i] = sc.nextInt();
                }
                System.out.println("#" + t + " " + maxValue(nums));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    static int maxValue(int[] nums){
        int T[][] = new int[nums.length][nums.length];
        int[][] order = new int[nums.length][nums.length];

        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i <= nums.length - len; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {

                    int leftValue = 1;
                    int rightValue = 1;
                    if (i != 0) {
                        leftValue = nums[i-1];
                    }
                    if (j != nums.length -1) {
                        rightValue = nums[j+1];
                    }

                    int before = 0;
                    int after = 0;
                    if (i != k) {
                        before = T[i][k-1];
                    }
                    if (j != k) {
                        after = T[k+1][j];
                    }
                    int value = leftValue * nums[k] * rightValue + before + after;
                    if(value>T[i][j]) {
                        order[i][j]=k;
                        T[i][j] = value;
                    }
                }
            }
        }
        for(int i=0;i<nums.length;i++){
            System.out.println(Arrays.toString(order[i]));
        }
        printOrder(order, nums, 1, 0, nums.length-1);
        System.out.println();
        return T[0][nums.length - 1];
    }

   static void printOrder(int[][] order, int[] num,  int count, int i, int j){
       if(count==order.length+1) return;
       int idx = order[i][j];
       int val = num[idx];
       int start=i, end=j;
       if(idx>=end) end = idx-1;
       if(idx<=start) start = idx+1;
       System.out.print(val+" ");
       printOrder(order, num, count+1,start, end);
    }
}
