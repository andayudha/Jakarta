package practice;

/**
 * Created by anda on 3/15/2016.
 */
public class MaxRectangle {

    public static void main(String[] args){
        int[][] A = new int[][]{
                {1,1,1,0},
                {1,1,1,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,0,1},
                {1,1,1,1}
        };//8
        System.out.println(maxRectangle(A));

        A = new int[][]{
                {1,1,1,0},
                {1,1,0,1},
                {0,1,0,0},
                {0,0,1,1},

        };//4
        System.out.println(maxRectangle(A));

        A = new int[][]{
                {1,0,1},
                {0,1,0},
                {1,0,1}
        };//1
        System.out.println(maxRectangle(A));

        A = new int[][]{
                {0,0},
                {0,0}
        };//0
        System.out.println(maxRectangle(A));

        A = new int[][]{
                {1}
        };//1
        System.out.println(maxRectangle(A));
    }

    public static int maxRectangle(int[][] A){
        int max = 0;
        int N = A.length; int M = A[0].length;
        int[] T = new int[M];
        for(int i=0;i<M;i++) T[i]=A[0][i];
        max = HistogramArea.largestRectangleArea(T);

        for(int i=1;i<N;i++){
            for(int j=0;j<M;j++){
                if(A[i][j]==1) T[j]++;
                else T[j]=0;
            }
            max = Math.max(max, HistogramArea.largestRectangleArea(T));
        }

        return max;
    }
}
