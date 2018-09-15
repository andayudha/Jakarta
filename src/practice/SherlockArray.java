/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by anda on 9/3/2016.
 */
public class SherlockArray {
    public static void main(String[] args){
     try {
         Scanner sc = new Scanner(new File("sherlock.txt"));
         int tc = sc.nextInt();
         for(int t=1; t<=tc;t++){
             int n = sc.nextInt();
             int[] a = new int[n];
             Solve s = new Solve(n);
             for(int i=0;i<n;i++){
                 a[i] = sc.nextInt();
                 s.input(i, a[i]);
             }
             s.solve();
             System.out.println(s.result?"YES":"NO");
         }

     }catch (IOException e){
        e.printStackTrace();
     }
    }

    static class Solve{
        int[] a;
        int n;
        int[] left, right;
        boolean result;

        public Solve( int n) {
            this.n = n;
            a = new int[n];
            left = new int[n]; right = new int[n];
            left[0]=0; right[n-1]=0;
        }

        void solve(){
//            process right
            if(n>1){
                right[n-2] = a[n-1];
                if(right[n-2]==left[n-2]){
                    result = true;
                    return;
                }
            }
            if(right[n-1]==left[n-1]){
                result = true;
                return;
            }

            if (n > 1) {
                for (int i = n - 2; i > 0; i--) {
                    right[i - 1] = right[i] + a[i];
                    if (right[i - 1] == left[i - 1]) {
                        result = true;
                        return;
                    }
                }
            }

            if(right[0]==left[0]){
                result = true;
                return;
            }
        }

        void input(int i, int val){
            a[i] = val;
            if(i>0 && i<n-1){
                //ambil dari kiri
                left[i+1] = left[i] + a[i];
            }
            if(i==0 && n>1){
                left[i+1] = a[i];
            }
        }
    }
}
