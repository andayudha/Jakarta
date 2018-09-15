
package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by SRIN on 7/21/2016.
 */
public class TownCenter {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("towncenter.txt"));
            int tc = sc.nextInt();
            for(int t=1;t<=tc;t++){
                int n = sc.nextInt();
                int c = sc.nextInt();
                int[] yGold = new int[c]; int[] xGold = new int[c];
                boolean[][] isGold = new boolean[n][n];
                for(int i=0;i<c;i++){
                    yGold[i]=sc.nextInt()-1;
                    xGold[i]=sc.nextInt()-1;
                    isGold[yGold[i]][xGold[i]]=true;
                }
                int[][]map = new int[n][n];

                //use solve 3
                int[][][] distance = new int[n][n][c];
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        map[i][j]=sc.nextInt();
                        for(int k=0;k<c;k++){
                            distance[i][j][k] = -1;
                        }
                    }
                }
                Solve3 s = new Solve3(map, n, c, yGold, xGold, distance);

                s.solve();
                System.out.println(""+s.result);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Solve3{
        int[][] map;
        int n, c;
        int[] yGold, xGold;
        int result = Integer.MAX_VALUE;
        int[][][] distance;

        public Solve3(int[][] map, int n, int c, int[] yGold, int[] xGold, int[][][] distance) {
            this.map = map;
            this.n = n;
            this.c = c;
            this.yGold = yGold;
            this.xGold = xGold;
            this.distance = distance;
            for(int i=0;i<c;i++){
                map[yGold[i]][xGold[i]]=2;
            }
        }

        public void solve() {
            for(int k=0;k<c;k++){
                traverse(yGold[k], xGold[k], 0, k);

            }



            //find minimum
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j]==0 || map[i][j]==2) continue;

                    int max = -1;
                    for(int k=0;k<c;k++){
                        max = Math.max(distance[i][j][k], max);
                    }
                    result = Math.min(max, result);
                }
            }
        }

        void traverse(int i, int j, int d, int idxGold){
            if(i<0 || i>=n || j<0 || j>=n) return;

            if(map[i][j]==0) return;

            if(distance[i][j][idxGold]==-1 /*unvisited*/ || d<distance[i][j][idxGold]){
                distance[i][j][idxGold] = d;
                traverse(i-1, j, d+1, idxGold);
                traverse(i+1, j, d+1, idxGold);
                traverse(i, j-1, d+1, idxGold);
                traverse(i, j+1, d+1, idxGold);
            }
//            int cd = d+1;
//            if(map[i][j]!=2) distance[i][j][idxGold] = cd;
//
//            if(i-1>=0 && distance[i-1][j][idxGold]==-1 && map[i-1][j]==1){
//                traverse(i-1, j, cd,  idxGold);
//            }
//            if(i+1<n && distance[i+1][j][idxGold]==-1 && map[i+1][j]==1){
//                traverse(i+1, j, cd,  idxGold);
//            }
//            if(j-1>=0 && distance[i][j-1][idxGold]==-1 && map[i][j-1]==1){
//                traverse(i, j-1, cd,  idxGold);
//            }
//            if(j+1<n && distance[i][j+1][idxGold]==-1 && map[i][j+1]==1){
//                traverse(i, j+1, cd,  idxGold);
//            }
        }
    }
}
