package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by SRIN on 7/13/2016.
 */
public class IceClimber {

    public static void main(String[] args){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("iceclimber.txt"));


        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int xs=0, ys=0, xf=0, yf =0;
            int col = sc.nextInt();
            int row = sc.nextInt();
            int[][] data = new int[row][col];
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    int x = sc.nextInt();
                    if(x==2){
                        xs = j; ys=i;
                    }
                    if(x==3){
                        xf = j; yf=i;
                    }
                    data[i][j]=x;
                }
            }
            Solution s = new Solution(row, col, xs, ys, xf, yf, data);
//            s.traverse(ys, xs, 0);
            s.solve();
            System.out.println("level : "+s.result);
        }

        sc.close(); // close the scanner object
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Solution{
        int row, col;
        int xs, ys, xf, yf;
        int[][] data;
        boolean[][] visited;
        int result;

        public Solution(int row, int col, int xs, int ys, int xf, int yf, int[][] data) {
            this.row = row;
            this.col = col;
            this.xs = xs;
            this.ys = ys;
            this.xf = xf;
            this.yf = yf;
            this.data = data;
            this.result=row;
            visited = new boolean[row][col];
        }

        int check(int y, int x, int level){
            log("("+y+","+x+")");
            if(x==xf && y==yf) {
                result = Math.max(result, level);
                return level;
            }

            visited[y][x] = true;

            int min = Integer.MAX_VALUE;

            //kiri
            if(x-1>=0 && data[y][x-1]!=0 && !visited[y][x-1]){
                int left = check(y, x-1, level);
                visited[y][x-1] = false;
                min = Math.min(min, left);
            }

            //kanan
            if(x+1<col && data[y][x+1]!=0 && !visited[y][x+1]){
                int right = check(y, x+1, level);
                visited[y][x+1] = false;
                min = Math.min(min, right);
            }

            //Atas
            int l = -1;
            for (int h = y-1; h>=0; h--) {
                if (data[h][x]==1){
                    l = y-h;
                    break;
                }
            }
            if (l>=0 && l<=level) {
//                int delta = Math.max(l, level);
                if(y-l>=0 && !visited[y-l][x]){
                    int up = check(y-l, x, level);
                    visited[y-l][x] = false;
                    min = Math.min(min, up);

                }

            }

            //Bawah
            l = -1;
            for (int h = y+1; h<row; h++) {
                if (data[h][x]==1){
                    l = h-y;
                    break;
                }
            }
            if (l>=0 && l<=level) {
//                int delta = Math.max(l, level);
//                int delta = l;
                if(y+l<row && !visited[y+l][x]){
                    int down = check(y+l, x, level);
                    visited[y+l][x] = false;
                    min = Math.min(min, down);
                }

            }

            log("min : "+min+"; level : "+level);

            return min;


        }

        private boolean checkLevel(int y, int x, int level) {
            log("("+y+","+x+")");
            if(x==xf && y==yf) {
                return true;
            }

            visited[y][x] = true;

            boolean up =false, down=false, right=false, left=false;

            //atas
            int yCheck=-1;
            for(int h=y-1;h>=0;h--){
                if(data[h][x]>0){
                    yCheck=h;
                    break;
                }
            }
            int delta = y-yCheck;
            if (yCheck!=-1 && delta<=level){
                if(y-delta>=0 && data[y-delta][x]>0 && !visited[y-delta][x]){
                    up = checkLevel(y-delta, x, level);
                    visited[y-delta][x]=false;
                }
            }


            //kiri
            if(x-1>=0 && data[y][x-1]!=0 && !visited[y][x-1]){
                left = checkLevel(y, x-1, level);
                visited[y][x-1] = false;
            }

            //bawah
            yCheck=-1;
            for(int h=y+1;h<row;h++){
                if(data[h][x]>0){
                    yCheck=h;
                    break;
                }
            }
            delta = yCheck-y;
            if (yCheck!=-1 && delta<=level){
                if(y+delta<row && data[y+delta][x]>0 && !visited[y+delta][x]){
                    down = checkLevel(y+delta, x, level);
                    visited[y+delta][x]=false;
                }
            }


            //kanan
            if(x+1<col && data[y][x+1]!=0 && !visited[y][x+1]){
               right = checkLevel(y, x+1, level);
                visited[y][x+1] = false;
            }

            return up||down||left||right;
        }

        private boolean checkDif(int y, int x, int level, int max) {
            log("("+y+","+x+")");
            if(x==xf && y==yf) {
                result = Math.min (result, max);
                return true;
            }

            visited[y][x] = true;

            boolean up =false, down=false, right=false, left=false;

            //atas
            int yCheck=-1;
            for(int h=y-1;h>=0;h--){
                if(data[h][x]>0){
                    yCheck=h;
                    break;
                }
            }
            int delta = y-yCheck;
            if (yCheck!=-1 ){
                if(y-delta>=0 && data[y-delta][x]>0 && !visited[y-delta][x]){
                    int m = Math.max(delta, max);
                    up = checkDif(y - delta, x, delta, m);
                    visited[y-delta][x]=false;
                }
            }


            //kiri
            if(x-1>=0 && data[y][x-1]!=0 && !visited[y][x-1]){
//                int m = Math.max(delta, max);
                left = checkDif(y, x - 1, level, max);
                visited[y][x-1] = false;
            }

            //bawah
            yCheck=-1;
            for(int h=y+1;h<row;h++){
                if(data[h][x]>0){
                    yCheck=h;
                    break;
                }
            }
            delta = yCheck-y;
            if (yCheck!=-1){
                if(y+delta<row && data[y+delta][x]>0 && !visited[y+delta][x]){
                    int m = Math.max(delta, max);
                    down = checkDif(y + delta, x, delta, m);
                    visited[y+delta][x]=false;
                }
            }


            //kanan
            if(x+1<col && data[y][x+1]!=0 && !visited[y][x+1]){
//                int m = Math.max(delta, max);
                right = checkDif(y, x + 1, level, max);
                visited[y][x+1] = false;
            }

            return up||down||left||right;
        }

        public void traverse(int y, int x, int level){
            log("("+y+","+x+")");
            if(x==xf && y==yf) {
                result = Math.min (result, level);
                return ;
            }

            visited[y][x] = true;

            //atas
            int yCheck=-1;
            for(int h=y-1;h>=0;h--){
                if(data[h][x]>0){
                    yCheck=h;
                    break;
                }
            }
            int delta = y-yCheck;
            if (yCheck!=-1 ){
                if(y-delta>=0 && data[y-delta][x]>0 && !visited[y-delta][x]){
                    int m = Math.max(delta, level);
                    traverse(y-delta, x,  m);
                    visited[y-delta][x]=false;
                }
            }


            //kiri
            if(x-1>=0 && data[y][x-1]!=0 && !visited[y][x-1]){
                 traverse(y, x - 1, level);
                visited[y][x-1] = false;
            }

            //bawah
            yCheck=-1;
            for(int h=y+1;h<row;h++){
                if(data[h][x]>0){
                    yCheck=h;
                    break;
                }
            }
            delta = yCheck-y;
            if (yCheck!=-1){
                if(y+delta<row && data[y+delta][x]>0 && !visited[y+delta][x]){
                    int m = Math.max(delta, level);
                    traverse(y + delta, x, m);
                    visited[y+delta][x]=false;
                }
            }


            //kanan
            if(x+1<col && data[y][x+1]!=0 && !visited[y][x+1]){
                traverse(y, x + 1, level);
                visited[y][x+1] = false;
            }
        }

        private void log(String s) {
//            System.out.println(s);
        }

        public void solve() {
//            long start = System.currentTimeMillis();
            for(int l=1;l<row;l++){
                if(checkLevel(ys, xs, l)){
                    result = l;
//                    long end = System.currentTimeMillis();
//                    System.out.println("time : "+(end-start));
                    return;
                }
            }
        }


    }
}
