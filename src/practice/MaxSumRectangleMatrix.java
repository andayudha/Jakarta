/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

/**
 * Created by anda on 9/11/2016.
 */
public class MaxSumRectangleMatrix {

    public static void main(String[] args){
        int[][] a = new int[][] {
                {2, 1, -3, -4, 5},
                {0, 6, 3, 4, 1},
                {2, -2, -1, 4, -5},
                {-3, 3, 1, 0, 3},
        };
        System.out.println(maxSumRect(a));
    }

    static int maxSumRect(int[][] a){
        int max = -1;
//        int up, down;
        int maxUp = 0, maxDown = 0;
        int maxLeft = 0, maxRight = 0;
        int row = a.length;
        for(int up=0;up<row;up++){
            int col = a[0].length;
            int[] t = new int[col];
            for(int down=up;down<row;down++){
                for(int k=0;k<col;k++){
                    t[k] = t[k] + a[down][k];
                }
                Kadane kadane = kadane(t);
                if(kadane.max>max){
                    max = kadane.max;
                    maxUp = up; maxDown = down;
                    maxLeft = kadane.start; maxRight = kadane.end;
                }
            }
        }
        System.out.println(maxUp+" "+maxDown+" "+maxLeft+" "+maxRight);
        return max;
    }

    private static Kadane kadane(int arr[]){
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
        return new Kadane(max, maxStart, maxEnd);
    }

    static class Kadane{
        int max, start, end;

        public Kadane(int max, int start, int end) {
            this.max = max;
            this.start = start;
            this.end = end;
        }
    }
}
