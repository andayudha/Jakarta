package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anda on 2/6/2016.
 */
public class MatrixTrtacing {

    public static void main(String[] args){
//        System..println(new Solve().factorial(6));
        String text = "BAABABAAABABAAA";
        String pattern = "AA";

        int count = 0;
        int start = 0;
        System.out.println(Arrays.toString(getAllMatches(text, pattern).toArray()));

    }

    static List<Integer> getAllMatches(String text, String pattern){
        if(text.indexOf(pattern)<0) return new ArrayList<Integer>();
        List<Integer> matches = new ArrayList<Integer>();
        int start = 0;
        while(start<text.length()-pattern.length()){
            int found = text.indexOf(pattern, start);
            start = found+1;
            matches.add(found);
        }
        return matches;
    }

    static class Solve{

        String[][] A;
        int M,N;
        String S = "MATHEMATICS";

        public Solve(int m, int n, String[][] a){
            this.A = a; this.M = m; this.N = n;

        }

        public int solve(){
            int total = computeAllPosibleWay(N, M);
            System.out.println("possible : "+total);

            int counter = 0;


            return counter;
        }

        int computeAllPosibleWay(int m, int n){
            return factorial(m+n-2)/(factorial(m-1)*factorial(n-1));
        }

        int factorial(int x){
            if(x==0) return 1;
            return x*(factorial(x-1));
        }
    }
}
