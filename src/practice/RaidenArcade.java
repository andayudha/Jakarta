
package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 7/23/2016.
 */
public class RaidenArcade {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("raiden.txt"));
            int tc = sc.nextInt();
            for(int t=1;t<=tc;t++){
                int n = sc.nextInt();
                int[][] map = new int[n][5];
                for(int i=0;i<n;i++){
                    for(int j=0;j<5;j++){
                        map[i][j] = sc.nextInt();
                    }
                }
                Solve s = new Solve(n, map);
                s.solve();
                System.out.println("#" + t + " " + (s.reachEnd? s.result : -1));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Solve {
        int result = 0;
        int n; boolean reachEnd;
        int[][] map;
        boolean bombUsed;

        public Solve(int n, int[][] map) {
            this.n = n;
            this.map = map;
        }

        void solve(){
            traverse(n, 2, 0, map);
        }

        void traverse(int i, int j, int coins, int[][] arena){
            if(i<0){
                result = Math. max(result, coins);
                reachEnd = true;
                return;
            }
            //gain action
            if(i<n && arena[i][j]==1) coins++;
            if(i<n && arena[i][j]==2) coins--;


            log("i = " + i + " coins = " + coins);

            if(coins==-1) return;

            //move left
            if(j-1>=0){
                traverse(i-1, j-1, coins, arena);
                if(!bombUsed){
                    bombUsed=true;
                    traverse(i-1, j-1, coins, getBombedArea(i-1, arena));
                    bombUsed = false;
                }
            }

            //move right
            if(j+1<5){
                traverse(i-1, j+1, coins, arena);
                if(!bombUsed){
                    bombUsed=true;
                    traverse(i-1, j+1, coins, getBombedArea(i-1, arena));
                    bombUsed = false;
                }
            }

            //stay
            traverse(i-1, j, coins, arena);
            if(!bombUsed){
                bombUsed=true;
                traverse(i-1, j, coins, getBombedArea(i-1, arena));
                bombUsed = false;
            }
        }

        int[][] getBombedArea(int r, int[][] arena){
            int[][] bombed = new int[n][5];

            //after
            for(int i=0; i<r-5+1; i++){
                System.arraycopy(arena[i], 0, bombed[i], 0, 5);
            }

            for(int i=r; i>=r-5+1&&i>=0 ;i--){
                for(int j=0;j<5;j++){
                     if(arena[i][j]!=2) bombed[i][j]=arena[i][j];
                }
            }

            //before
            for(int i=r+1; i<n; i++){
                System.arraycopy(arena[i], 0, bombed[i], 0, 5);
            }
            return bombed;
        }

        private void log(String s) {
//            System.out.println(s);
        }

        private void printArena(int[][] arena) {
            for(int[] x : arena) System.out.println(Arrays.toString(x));
            System.out.println();
        }
    }
}
