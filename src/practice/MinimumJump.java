package practice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 2/14/2016.
 */
public class MinimumJump {

    public static void main(String[] args){

        try{
//            Scanner sc = new Scanner(new File("Large.txt"));
//            int count =0;
//            sc.nextLine();
//            while(sc.hasNext()){
//                count++;
//            }
//            System.out.println(count);

            Scanner sc = new Scanner(new File("MinimumJump.txt"));
            int T = sc.nextInt();
            for(int t=1;t<=T;t++){
                int n = sc.nextInt();
                int[] a = new int[n];
                for(int i=0;i<n;i++){
                    a[i]=sc.nextInt();
                }
                int min = getMinimumJump(n, a);
                int ex = sc.nextInt();
                System.out.println("test case "+t+" : "+(min==ex));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int getMinimumJump(int n, int[]data){
        if(n >1 && data[0] == n-2) return 1;
        int[] jump = new int[n];
        for(int i=1;i<n;i++) jump[i] = Integer.MAX_VALUE-1;

        int[] from = new int[n];
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(j+data[j]>=i){
                    if(jump[i] > jump[j] + 1){
                        from[i] = j;
                        jump[i] = jump[j] + 1;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(jump));
        System.out.println(Arrays.toString(from));
        if(jump[n-1]==Integer.MAX_VALUE-1) return -1; //unreachable
        return jump[n-1];
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] jump = new int[n];
        for(int i=1;i<n;i++) jump[i] = Integer.MAX_VALUE-1;

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(j+nums[j]>=i){
                    jump[i] = Math.min(jump[i] , jump[j] + 1);
                }
            }
        }
        if(jump[n-1]==Integer.MAX_VALUE-1) return -1; //unreachable
        return jump[n-1];
    }
}
