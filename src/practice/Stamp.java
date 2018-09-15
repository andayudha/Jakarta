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
 * Created by anda on 8/21/2016.
 */
public class Stamp {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("stamp.txt"));
        int tc = sc.nextInt();
        for(int t=1;t<=tc;t++){
            int row = sc.nextInt();
            int col = sc.nextInt();
            int[][] data = new int[row][col];
            Solve s = new Solve(row, col);
            sc.nextLine();
            for(int i=0;i<row;i++){
                String line = sc.next();
                int x=0;
                for(int j=0;j<col;j++){
                    s.input(i+1, j+1, line.charAt(j));
                }
            }

            s.printMatrix(s.T);
            System.out.println();
            s.solve();
            System.out.println(""+s.result);
        }

    }


    static class Solve{
        int row, col;
        int[][] T;
        int[][] max;
        char[][] data;
        int result = Integer.MAX_VALUE;

        public Solve(int row, int col) {
            this.row = row;
            this.col = col;
            data = new char [row+1][col+1];
            T = new int[row+1][col+1];
            max = new int[row+1][col+1];
        }

        void input(int i, int j, char val){
            data[i][j] = val;
            if(val!='#'){
                T[i][j] = Math.min(T[i][j-1], Math.min(T[i-1][j], T[i-1][j-1]))+1;
            }
        }

        void solve(){
            //put max
            for(int i=row;i>=1;i--){
                for(int j=col;j>=1;j--){
                    int dim = T[i][j];
                    for(int y=i-dim+1;y<=i;y++){
                        for(int x=j-dim+1;x<=j;x++){
                            max[y][x] = Math.max(max[y][x], dim);
//                            printMatrix(max);
                        }
                    }
                }
            }
            printMatrix(max);

            //find max
            for(int i=1;i<=row;i++){
                for(int j=1;j<col;j++){
                    if(max[i][j]!=0 && data[i][j]!='?'){
                        result = Math.min(result, max[i][j]);
                    }
                }
            }
        }

        void printMatrix(int[][] matrix){
            for(int i=0; i<row+1;i++) System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
