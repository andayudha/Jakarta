/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

/**
 * Created by anda on 5/14/2016.
 */
public class SparseTableRMQ {

    public static void main(String args[]) {
//        int[] input = {2, 5, 3, 6, 4, 1, -1, 3, 4, 2};
        int[] input = {4,6,1,5,7,3};
        SparseTableRangeMinimumQuery sparse = new SparseTableRangeMinimumQuery(input);
//        for (int i = 0; i < input.length; i++) {
//            for (int j = i; j < input.length; j++) {
//                System.out.print(sparseTableRangeMinimumQuery.rangeMinimumQuery(i, j) + " ");
//            }
//            System.out.println();
//        }
        System.out.println("" + sparse.rangeMinimumQuery(3, 5));
    }

    static class SparseTableRangeMinimumQuery {

        private final int[][] sparse;
        private final int n;
        private final int[] input;

        public SparseTableRangeMinimumQuery(int[] input) {
            this.input = input;
            this.n = input.length;
            this.sparse = preprocess(input, this.n);
        }

        private int[][] preprocess(int[] input, int n) {
            int[][] sparse = new int[n][log2(n) + 1];
            for (int i = 0; i < input.length; i++) {
                sparse[i][0] = i;
            }

            for (int j = 1; 1 << j <= n; j++) {
                for (int i = 0; i + (1 << j) - 1 < n; i++) {
                    if (input[sparse[i][j - 1]] < input[sparse[i + (1 << (j - 1))][j - 1]]) {
                        sparse[i][j] = sparse[i][j - 1];
                    } else {
                        sparse[i][j] = sparse[i + (1 << (j - 1))][j - 1];
                    }
                }
            }

//            for(int j=1;Math.pow(2,j)<n; j++){
//                for(int i=0;i+Math.pow(2,j)-1<n;i++){
//                    int minPrefix = sparse[i][j-1];
//                    int minSufix = sparse[((int) (i + Math.pow(2, j - 1)))] [j-1];
//                    sparse[i][j] = input[minPrefix]<input[minSufix] ?  minPrefix : minSufix;
//                }
//            }
            return sparse;
        }

        public int rangeMinimumQuery(int low, int high) {
            int l = high - low + 1;
            int k = log2(l);
//            if (input[sparse[low][k]] <= input[sparse[low + l - (1<<k)][k]]) {
//                return input[sparse[low][k]];
//            } else {
//                return input[sparse[high - (1<<k) + 1][k]];
//            }
            int left = input[sparse[low][k]];
            int right = input[sparse[((int) (low + l - Math.pow(2, k)))][k]];
            return Math.min(left,right);
        }

        private int log2(int n){
            if(n <= 0) throw new IllegalArgumentException();
            return 31 - Integer.numberOfLeadingZeros(n);
        }


    }
}
