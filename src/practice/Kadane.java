/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

/**
 * Created by anda on 9/10/2016.
 */
public class Kadane {

    public static void main(String[] args){
        int[] a = new int[]{-1, 3,2, -2};
        K k = kadane(a);
        System.out.println();
    }

    private static K getMax(int[] a) {
        int max = Integer.MIN_VALUE;
        int maxCurrent = a[0];
        int currentStart=0;
        int start = 0;
        int end = 0;
        for(int i=0;i<a.length;i++){
            if(a[i]>maxCurrent+a[i]){
                maxCurrent = a[i];
                currentStart = i;
//                end = i;
            }else{
                maxCurrent = maxCurrent+a[i];
//                end = i;
            }
            if(maxCurrent>max){
                max = maxCurrent;
                start = currentStart;
                end = i;
            }
        }
        return new K(max, start, end);
    }

    private static K kadane(int arr[]){
        int max = 0;
        int maxStart = -1;
        int maxEnd = -1;
        int currentStart = 0;
        int maxSoFar = 0;
        for(int i=0; i < arr.length; i++){
            maxSoFar += arr[i];
            if(maxSoFar < 0){
                maxSoFar = 0;
                currentStart = i+1;
            }
            if(max < maxSoFar){
                maxStart = currentStart;
                maxEnd = i;
                max = maxSoFar;
            }
        }
        return new K(max, maxStart, maxEnd);
    }

    static class K{

        int sum, start, end;

        public K(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
    }
}
