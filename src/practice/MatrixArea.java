package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 2/13/2016.
 */
public class MatrixArea {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("MatrixArea.txt"));
            int T = sc.nextInt();
            for(int t=1;t<=T;t++){
                int rows = sc.nextInt(); int cols = sc.nextInt();
                int[][] mat = new int[rows][cols];
                for(int i=0;i<rows;i++){
                    for(int j=0;j<cols;j++){
                        mat[i][j]=sc.nextInt();
                    }
                }
                int ex = sc.nextInt();
                System.out.println(maximalRectangle(mat)==ex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        char[] = new char[]{"as","ab"};
    }

    public static int maximalRectangle(char[][] matrix) {
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] area = new int[rows][cols];

        //construct area matrix
        for(int j=0;j<cols;j++) area[0][j] = Character.getNumericValue(matrix[0][j]);
        for(int i=0;i<rows;i++) area[i][0] = Character.getNumericValue(matrix[i][0]);

        for(int i=1;i<rows;i++){
            for(int j = 1; j<cols;j++){
                if(i>0 && j>0){
                    if(matrix[i][j]=='1'){
                        area[i][j]=Math.min(area[i-1][j], Math.min(area[i][j-1], area[i-1][j-1])) + 1;
                    }else{
                        area[i][j]=0;
                    }
                    max = Math.max(area[i][j], max);
                }
            }
            System.out.println(Arrays.toString(area[i]));
        }

        return max;
    }


    public static int maximalRectangle(int[][] matrix) {
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] area = new int[rows][cols];

        //construct area matrix
        for(int j=0;j<cols;j++) area[0][j] = matrix[0][j];
        for(int i=0;i<rows;i++) area[i][0] = matrix[i][0];

        for(int i=1;i<rows;i++){
            for(int j=1; j<cols;j++){
//                if(i>0 && j>0){
                    if(matrix[i][j]==1){
                        area[i][j]=Math.min(area[i-1][j], Math.min(area[i][j-1], area[i-1][j-1])) + 1;
                    }else{
                        area[i][j]=0;
                    }
                    max = Math.max(area[i][j], max);
//                }
            }
            System.out.println(Arrays.toString(area[i]));
        }

        return max;
    }


}
