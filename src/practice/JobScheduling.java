package practice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by anda on 3/15/2016.
 */
public class JobScheduling {

    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(new File("Scheduling.txt"));
            int N = sc.nextInt();
            Schedulling schedulling = new Schedulling(N);
            for(int i=0;i<N;i++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                int val = sc.nextInt();
                schedulling.add(i, from, to, val);
            }
            System.out.println(schedulling.solve());
        }catch (IOException e){

        }
    }

    static class Schedulling{
        int N;
        Job[] jobs;
        int[] T;

        public Schedulling(int n) {
            N = n;
            jobs = new Job[N];
            T = new int[N];
        }

        public int solve(){
            Arrays.sort(jobs);
            for(int i=0;i<N;i++) T[i] = jobs[i].val;
            for(int i=1;i<N;i++){
                for(int j=0;j<i;j++){
                    if(isNotOverlap(jobs[j], jobs[i])){
                        T[i] = Math.max(T[i], T[j] + jobs[i].val);
                    }
                }
            }
            int max = -1;
            for(int t : T) max = Math.max(max, t);
            return max;
        }

        private boolean isNotOverlap(Job j, Job i) {
            return (j.to<=i.from);
        }

        public void add(int i, int from, int to, int val){
            jobs[i]= new Job(from, to, val);
        }

        static class Job implements Comparable<Job>{
            int from, to, val;

            public Job(int from, int to, int val) {
                this.from = from;
                this.to = to;
                this.val = val;
            }

            @Override
            public int compareTo(Job o) {
                if(this.to>o.to) return 1;
                if(this.to<o.to) return -1;
                return 0;
            }
        }
    }
}
