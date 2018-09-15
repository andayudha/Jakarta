/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package hackerrank;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

/**
 * Created by anda on 6/27/2018.
 */
public class Pairs {
    static int total;

    // Complete the pairs function below.
    static int pairs(int k, int[] arr) {
        total = 0;
//        int[] pair = new int[2];
//        pairing(arr, arr.length, 2, 0, pair, 0, k);
        pairing2(arr, k);
        return total;
    }

    private static void pairing2(int[] arr, int k) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for(int x : arr){
            if(countMap.containsKey(x)){
                countMap.put(x, countMap.get(x)+1);
            }else{
                countMap.put(x, 1);
            }
        }
        for(int x : arr){
            int sum = x+k;
            int p  = Integer.MAX_VALUE;
            total+= countMap.containsKey(sum) ? 1 : 0;
        }
    }

    static void pairing(int arr[], int n, int r, int index,
                                int pair[], int i, int k) {
        if (index == r){
            total += Math.abs(pair[0]-pair[1])==k ? 1 : 0;
            return;
        }
        if (i >= n)   return;
        pair[index] = arr[i];
        pairing(arr, n, r, index+1, pair, i+1, k);
        pairing(arr, n, r, index, pair, i+1, k);
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("Pairs.txt"));
        int tc = scanner.nextInt();
        for(int t=1;t<=tc;t++){
//            String[] nk = scanner.nextLine().split(" ");
//            int n = Integer.parseInt(nk[0]);
//            int k = Integer.parseInt(nk[1]);
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                int arrItem = scanner.nextInt();
                arr[i] = arrItem;
            }

            int result = pairs(k, arr);
            System.out.println(result+"");
        }


        scanner.close();
    }
}
