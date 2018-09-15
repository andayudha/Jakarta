package practice;

import data_structure.XList;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 2/14/2016.
 */
public class Knapsack01 {

    public static void main(String[] args){
//        Item[] items = new Item[]{new Item(3, 4),new Item(5, 4),new Item(1, 4),new Item(4, 4)};
//        Arrays.sort(items);
//        System.out.println(Arrays.toString(items));
        try {
            Scanner sc = new Scanner(new File("KnapSack01.txt"));
            int T = sc.nextInt();
            for(int tc=1;tc<=T;tc++){
                int c = sc.nextInt(); int n = sc.nextInt();
                int[] weight = new int[n];
                int[] value = new int[n];
                for(int i=0;i<n;i++){
                    weight[i]=sc.nextInt();
                    value[i]=sc.nextInt();
                }
                int ex = sc.nextInt();
                System.out.println(ex==getMaxValue(n, c, weight, value));
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static int getMaxValue(int n, int capacity, int[] weight, int[] value){
        int max =0;
        int[][] T = new int[n][capacity+1];

        Item[] items = new Item[n];

        for(int i=0;i<n;i++) items[i]= new Item(weight[i], value[i]);
        Arrays.sort(items);

        //set first colom to zero
        for(int i=0;i<n;i++) T[i][0]=0;

        for(int i=0;i<n;i++){
            if(i==0){
                for(int j=1;j<=capacity;j++){
                    if(items[i].weight>j) T[i][j] = 0;
                    else T[i][j] = items[i].value;
                    max = Math.max(max, T[i][j]);
                }
            }else{
                for(int j=1;j<=capacity;j++){
                    if(items[i].weight>j){
                        T[i][j] = T[i-1][j];
                    }else{
                        T[i][j] = Math.max(items[i].value + T[i-1][j- items[i].weight], T[i-1][j]);
                    }
                    max = Math.max(max, T[i][j]);
                }
            }
            System.out.println(Arrays.toString(T[i]));
        }

        //print matrix
//        for(int i=0;i<n;i++) System.out.println(Arrays.toString(T[i]));
        printPicked(T, items.length-1, capacity, items);

        return max;
    }

    private static void printPicked(int[][] T, int i, int j, Item[] item){
        int currentValue = T[i][j];
        if(currentValue==0 || (i==0&& j==0)){
            return;
        }
        int top = T[i-1][j];
        if(currentValue!=top){
            System.out.println(item[i]);
            printPicked(T, i-1, j-item[i].weight, item);
        }else{
            printPicked(T, i-1, j, item);
        }
    }

    static class Item implements Comparable<Item>{
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item item) {
            return this.weight-item.weight;
        }

        @Override
        public String toString() {
            return "("+this.weight+","+this.value+")";
        }
    }
}
