package practice;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by anda on 3/13/2016.
 */
public class MinShopping2 {

    static class MinShoppingCost{
        int N;
        int T[][];
        int[] a, b, c;  //items
        int result;

        public MinShoppingCost(int n) {
            N = n;
            T = new int[N][4];
            for(int i=0;i<N;i++){
                for(int j=0;j<4;j++){
                    T[i][j]=-1;
                }
            }
            a = new int[n]; b = new int[n]; c = new int[n];
        }

        private int minCost(int i, int n, int item){
            if(i>=n) return 0;
            if(T[i][item]!=-1){
                return T[i][item];
            }
            if(item==0){
                return T[i][item] = Math.min(b[i] + minCost(i+1, n , 1), c[i] + minCost(i+1, n, 2));
            }
            if(item==1){
                return T[i][item] = Math.min(a[i] + minCost(i + 1, n, 0), c[i] + minCost(i + 1, n, 2));
            }
            if(item==2){
                return T[i][item] = Math.min(b[i] + minCost(i+1, n , 1), a[i] + minCost(i+1, n, 0));
            }
            return Integer.MAX_VALUE;
        }

        public void solve(){
            result = Math.min(a[0] + minCost(1, N, 0), Math.min(b[0] + minCost(1, N, 2), c[0] + minCost(1, N, 2)));
        }

        public void add(int i, int aa, int bb, int cc){
            a[i]=aa; b[i]=bb; c[i]=cc;
        }
    }
}
