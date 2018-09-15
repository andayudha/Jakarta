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
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by anda on 8/20/2016.
 */
public class Interstelar {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("interstellar.txt"));
            int tc = sc.nextInt();
            for(int t=1;t<=tc;t++){
                int xStart = sc.nextInt(); int yStart = sc.nextInt();
                int xEnd = sc.nextInt(); int yEnd = sc.nextInt();
                int n = sc.nextInt();
                WormHole[] wormHoles = new WormHole[n];
                for(int i=0;i<n;i++){
                    int xA = sc.nextInt(); int yA = sc.nextInt();
                    int xB = sc.nextInt(); int yB = sc.nextInt();
                    wormHoles[i] = new WormHole(xA, yA, xB, yB);
                }
                Solve s = new Solve(n, wormHoles, xStart, yStart, xEnd, yEnd);
                s.solve();
                System.out.println(""+s.result);
            }
            sc.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    static class Solve{
        int n;
        WormHole[] wormHoles;
        int xStart, yStart, xEnd, yEnd;
        int result;

        public Solve(int n, WormHole[] wormHoles, int xStart, int yStart, int xEnd, int yEnd) {
            this.n = n;
            this.wormHoles = wormHoles;
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
            result = Math.abs(xStart-xEnd)+Math.abs(yStart-yEnd);
        }

        void solve(){
           for(int i=0;i<n;i++){
               int total = Math.abs(xStart - wormHoles[i].xA)+Math.abs(yStart - wormHoles[i].yA);
               if(total<result) permute(i, 1, wormHoles[i].xB, wormHoles[i].yB, new boolean[n], total);

               total = Math.abs(xStart - wormHoles[i].xB)+Math.abs(yStart-wormHoles[i].yB);
               if(total<result) permute(i, 1, wormHoles[i].xA, wormHoles[i].yA, new boolean[n], total);
           }
        }

        void permute(int i, int flag, int x, int y, boolean[] visited, int total){
            if(flag==0){
                result = Math.min(result, total);
//                System.out.println(total);
                return;
            }
            visited[i]=true;
            boolean[] v = visited.clone();

            int dEnd = total + Math.abs(x - xEnd)+Math.abs(y-yEnd);
            if (dEnd<result) permute(i, 0, xEnd, yEnd, v, dEnd);

            for(int w=0;w<n;w++){
                if(!visited[w]){
                    int xA = wormHoles[w].xA;  int yA = wormHoles[w].yA;
                    int xB = wormHoles[w].xB;  int yB = wormHoles[w].yB;

                    //to xA yA
                    int d = total + Math.abs(x-xA)+Math.abs(y-yA);
                    if(d<result){
                       v = visited.clone();
                        permute(w, 1, xB, yB, v, d);
                    }

                    //to xB yB
                    d = total + Math.abs(x-xB)+Math.abs(y-yB);
                    if(d<result){
                        v = visited.clone();
                        permute(w, 2, xA, yA, v, d);
                    }
                }
            }
        }

    }


    static class WormHole{
        int xA, yA, xB, yB;

        public WormHole(int xA, int yA, int xB, int yB) {
            this.xA = xA;
            this.yA = yA;
            this.xB = xB;
            this.yB = yB;
        }

    }
}
