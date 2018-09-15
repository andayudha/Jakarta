/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

import java.util.Arrays;

/**
 * Created by anda on 4/16/2016.
 */
public class CoinChangingMinimum {

    public static void main(String[] args){
        int[] coins = new int[]{7,2,3,6};
        int price = 13;
        getMinimumCoinChanging(coins, price);
    }

    private static int getMinimumCoinChanging(int[] coins, int price){
        int min;
        int[] use = new int[price+1]; Arrays.fill(use, -1);
        int[] T = new int[price+1]; Arrays.fill(T, 1, price + 1, Integer.MAX_VALUE - 1);

        for(int j=0;j<coins.length;j++){
            for(int i=1;i<=price;i++){
                if (coins[j]<=i && T[i - coins[j]] + 1 < T[i]) {
                    T[i] = 1 + T[i - coins[j]];
                    use[i] = j;
                }
            }
        }
        System.out.println(Arrays.toString(T));
        System.out.println(Arrays.toString(use));
        min = T[price];

        printUsed(coins, use, price);
        return min;
    }

    private static void printUsed(int[] coin, int[] use, int start){
        if(start<=0) return;
        int idx = use[start];
        int nom = coin[idx];
        System.out.println(nom);
        printUsed(coin, use, start-nom);
    }
}
