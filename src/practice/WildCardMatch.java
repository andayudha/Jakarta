package practice;

import java.util.Arrays;

/**
 * Created by anda on 3/12/2016.
 */
public class WildCardMatch {

    public static void main(String[] args){
        String string = "xaylmz"; String pattern = "x?y*z";
        isMatch(string, pattern);
        System.out.println("ab##c##x##".replaceAll("##", "#"));
    }

    public static boolean isMatch(String string, String pattern){
        int m = string.length()+1; int n = pattern.length()+1;
        boolean[][] T = new boolean[m][n];
        T[0][0]=true;
//        printMatrix(T, m);

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(string.charAt(i-1)==pattern.charAt(j-1) || pattern.charAt(j-1)=='?'){
                    T[i][j]=T[i-1][j-1];
                }else if(pattern.charAt(j-1)=='*'){
                    T[i][j]=T[i][j-1] || T[i-1][j];
                }
            }
        }

        printMatrix(T, m);

        return T[m-1][n-1];
    }

    static void printMatrix(boolean[][] matrix, int m){
        for(int i=0;i<m;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
