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
 * Created by anda on 9/4/2016.
 */
public class Stars {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("stars.txt"));
        int n = sc.nextInt();
        Solve s = new Solve(n);
        for(int i=0;i<n;i++){
            long x = sc.nextLong();
            long y = sc.nextLong();
            long val = sc.nextLong();
            s.input(i, x, y, val);
        }
        s.solve();
        System.out.println(s.result);
    }

    static class Solve{
        int n;
        long result = 0;
        long min = Long.MAX_VALUE;
        Point[] xpoints; Point[] ypoints;

        public Solve(int n) {
            this.n = n;
            xpoints = new Point[n];
            ypoints = new Point[n];
        }

        void solve(){
            Arrays.sort(xpoints);
            Arrays.sort(ypoints);

            //process X
            for(int i = 0;i <n-1;i++){
                //process X
                if(xpoints[i].pos<xpoints[i+1].pos){
                    long pivot = xpoints[i].pos + (xpoints[i+1].pos-xpoints[i].pos)/2;
                    int left = 0; int right = 0;
                    for(int idx=0; idx<n;idx++){
//                        if(xpoints[idx].pos<=pivot){
                        if(idx<=i) {
                            left += xpoints[idx].val;
//                        }else if(xpoints[idx].pos>pivot){
                        }else{
                            right+=xpoints[idx].val;
                        }
                    }
                    int delta = Math.abs(right-left);
                    if(delta<min){
                        min = delta;
                        result = Math.min(left, right);
                    }
                }
//                process Y
                if(ypoints[i].pos<ypoints[i+1].pos){
                    long pivot = ypoints[i].pos + (ypoints[i+1].pos-ypoints[i].pos)/2;
                    int up = 0; int down = 0;
                    for(int idx=0; idx<n;idx++){
//                        if(ypoints[idx].pos<=pivot){
                        if(idx<=i) {
                            up += ypoints[idx].val;
//                        }else if(ypoints[idx].pos>pivot){
                        }else{
                            down+=ypoints[idx].val;
                        }
                    }
                    int delta = Math.abs(down-up);
                    if(delta<min){
                        min = delta;
                        result = Math.min(up, down);
                    }
                }
            }
        }

        void input(int i, long x, long y, long val){
            xpoints[i] = new Point(x, val);
            ypoints[i] = new Point(y, val);
        }

    }

    static class Point implements Comparable<Point>{
        long val;
        long pos;

        public Point(long pos, long val) {
            this.val = val;
            this.pos = pos;
        }

        @Override
        public int compareTo(Point p) {
            return (int) (this.pos-p.pos);
        }
    }


}
