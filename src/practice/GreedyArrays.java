package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GreedyArrays {

    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("arrays.txt"));
//            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();
            for (int tc = 0; tc < T; tc++) {
                int N = sc.nextInt(); int K = sc.nextInt();
                int[] A = new int[N];
                for(int i=0;i<N;i++){
                    A[i]=sc.nextInt();
                }
                int[] B = new int[N];
                for(int i=0;i<N;i++){
                    B[i]=sc.nextInt();
                }
               System.out.println(getResult(N, A, B, K));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private static String getResult(int n, int[] a, int[] b, int k) {
        Arrays.sort(a);
        Integer[] B = new Integer[b.length];
        for (int i = 0; i < b.length; i++) {
            B[i] = Integer.valueOf(b[i]);
        }
        Arrays.sort(B, Collections.reverseOrder());
        for(int i=0;i<n;i++){
            if(a[i]+(int)B[i]<k) return "NO";
        }
        return "YES";
    }


}
