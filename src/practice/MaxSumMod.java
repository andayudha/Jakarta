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
import java.util.Scanner;

/**
 * Created by anda on 6/25/2016.
 */
public class MaxSumMod {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("MaxSumMod.txt"));
            int tc = sc.nextInt();
            for(int t=1;t<=tc;t++){
                int n = sc.nextInt();
                int m =sc.nextInt();
                long[] a = new long[n];
                for(int i=0;i<n;i++){
                    a[i]=sc.nextLong();
                }
                Solve solve = new Solve(m,n,a);
                solve.solve();
                System.out.println("#" + t + " " + solve.max);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static class Solve{
        long max = -1;
        long[][] T;
        long[] a;
        int n, m;

        public Solve(int m, int n, long[] a) {
            this.m = m;
            this.n = n;
            this.a = a;
            T = new long[n][n];
        }

        public void solve(){
            for(int i=0;i<n-1;i++){
                T[i][i]=a[i];
                long val = T[i][i]%m;
                max = Math.max(max, val);
                for(int j=i+1;j<n;j++){
                    T[i][j]=T[i][j-1]+a[i];
                    val = T[i][j]%m;
                    max = Math.max(max, val);
                }
            }
            long val = a[n-1]%m;
            max = Math.max(max, val);
        }
    }
}
