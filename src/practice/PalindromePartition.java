package practice;

import java.util.Arrays;

/**
 * Created by anda on 3/12/2016.
 */
public class PalindromePartition {

    public static void main(String[] args){
        System.out.println(minSplit("abcbm"));
        System.out.println(minSplit("ABCCDCCLMLCCD"));
        System.out.println(minSplit("abcde"));

    }

    public static int minSplit(String string){
        if(isPalindrome(string)) return 0;
        int n = string.length();
        int[][] T = new int[n][n];
        for(int t=2;t<=n;t++){// length of part
            for(int j=t-1;j<n;j++){
                int i = j-t+1;
                String sub = string.substring(i, j+1);
                if(isPalindrome(sub)){
                    T[i][j]=0;
                }else{
                    int min = n;
                    for(int k=i;k<=j-1;k++){
                        min = Math.min(min, T[i][k]+T[k+1][j]);
                    }
                    T[i][j]=1+min;
                }
            }

        }
        printMatrix(T, n);
        return T[0][n-1];

    }

    public static boolean isPalindrome(String s){
        int n = s.length();
        for(int i=0;i<n/2;i++){
            if(s.charAt(i)!=s.charAt(n-i-1)) return false;
        }
        return true;
    }

    static void printMatrix(int[][] matrix, int m){
        for(int i=0;i<m;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
