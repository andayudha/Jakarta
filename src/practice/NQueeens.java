package practice;


import java.util.Arrays;

public class NQueeens {

    public static void main(String[] args){
        int n = 4 ;
        int[] x  = new int[4];

//        BackTracking bt = new BackTracking(n);
//        bt.nQueen(0, n);
//        System.out.println(bt.c);

        Queens queens = new Queens(n);
        if (!queens.theBoardSolver(0)) {
            System.out.println("Solution not found.");
        }
        queens.printBoard();
    }

    static class BackTracking{
        int N,c;
        int[]x;

        public BackTracking(int n) {
            this.N = n;
            x = new int[this.N];
        }

        boolean isSafe(int k, int i) {
            for (int j = 0; j < k - 1; j++) {
                if (x[j] == i || (Math.abs(x[j]-i) == Math.abs(j-k))){
                    return false;
                }
            }
            return true;
        }

        void nQueen(int k, int n){
            for(int i=0;i<n;i++){
                if(isSafe(k,i)){
                    x[k]=i;
                    if(k==n-1){
                        c++;
                        System.out.println(n+""+Arrays.toString(x));
                    }else{
                        nQueen(k+1, n);
                    }
                }
            }
        }
    }

    static class Queens{
        int N;
        int[][] board;

        public Queens(int n) {
            N = n;
            board = new int[N][N];
        }

         boolean isSafe( int row, int col) {
            int i, j;
            for (i = 0; i < col; i++) {
                if (board[row][i] == 1)
                    return false;
            }
            for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 1)
                    return false;
            }
            for (i = row, j = col; j >= 0 && i < N; i++, j--) {
                if (board[i][j] == 1)
                    return false;
            }
            return true;
        }

         boolean theBoardSolver( int col) {
            if (col >= N)
                return true;
            for (int i = 0; i < N; i++) {
                if (isSafe( i, col)) {
                    board[i][col] = 1;
                    if (theBoardSolver( col + 1))
                        return true;
                    // Backtracking is hella important in this one.
                    board[i][col] = 0;
                }
            }
            return false;
        }

         void printBoard() {
            int i;
            for (i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    if (board[i][j] == 1) {
                        System.out.print("Q ");
                    } else {
                        System.out.print("_ ");
                    }
                System.out.println("\n");
            }
        }
    }

}
