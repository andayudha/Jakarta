package practice;

import java.util.Arrays;

/**
 * Created by anda on 3/6/2016.
 */
public class MinimumEditDistance {

    public static void main(String[] args){
        String from = "abcdef";
        String to = "azced";
        System.out.println(getMinimumEditDistance(from, to));
    }

    public static int getMinimumEditDistance(String from, String to){
        int row = to.length()+1;
        int col = from.length()+1;
        int[][] T = new int[row][col];
        for(int i=0;i<row;i++)T[i][0]=i;
        for(int j=0;j<col;j++)T[0][j]=j;

        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                if(from.charAt(j-1)==to.charAt(i-1)){
                    T[i][j] = T[i-1][j-1];
                }else{
                    T[i][j] = Math.min(T[i-1][j], Math.min(T[i][j-1], T[i-1][j-1])) + 1;
                }
            }
        }

        for(int i=0;i<row;i++)   System.out.println(Arrays.toString(T[i]));

        return T[row-1][col-1];
    }
}
