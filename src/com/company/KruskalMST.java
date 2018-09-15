package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by user on 9/12/2015.
 */
public class KruskalMST {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("01FollowtheDots_input.txt"));
            Scanner out = new Scanner(new File("01FollowtheDots_output.txt"));
//            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();
            for(int test_case = 0; test_case < T; test_case++) {
                int V = sc.nextInt();
                double [] xs = new double[V];
                double [] ys = new double[V];
                for(int i=0;i<V;i++){
                    double x = sc.nextDouble(); xs[i]=x;
                    double y = sc.nextDouble(); ys[i]=y;
                }
                MinPQ pq = new MinPQ();
                for(int i=0;i<V-1;i++){
                    for(int j=i+1;j<V;j++){
                        pq.insert(new Edge(i, j, Math.sqrt(Math.pow(xs[i]-xs[j],2) + Math.pow(ys[j]-ys[i],2))));
                    }
                }
                double total = 0; int counter = 0;
                UnionFind uf = new UnionFind(V);
                while(!pq.isEmpty() && counter<=V-1){
                    Edge e = (Edge) pq.delMin();
                    int v  = e.either(); int w = e.other(v);
                    if(!uf.isConnected(v, w)){
                        uf.union(v,w);
                        total+=e.weight; counter++;
                    }

                }

                String result = "#"+(test_case+1)+" "+String.format("%.2f", total);
                String answer = out.nextLine();
                System.out.println(result.equals(answer));
//                int E = sc.nextInt();
//                MinPQ pq = new MinPQ();
//                for(int i=0;i<E;i++){
//                    int u = sc.nextInt(); int v = sc.nextInt(); int w = sc.nextInt();
//                    pq.insert(new Edge(u,v,w));
//                }
//
//                int total = 0; int counter = 0;
//                UnionFind uf = new UnionFind(V);
//                while(!pq.isEmpty() && counter<=V-1){
//                    Edge e = (Edge) pq.delMin();
//                    int v  = e.either(); int w = e.other(v);
//                    if(uf.isConnected(v, w)) continue;
//                    uf.union(v,w);
//                    total+=e.weight; counter++;
//                }
//
//                String result = "#"+(test_case+1)+" "+total;
//                String answer = out.nextLine();
//                System.out.println(result.equals(answer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class UnionFind{
        int[] id; int count;

        public UnionFind(int n) {
            id = new int[n];
            count=n;
            for(int i=0;i<n;i++) id[i]=i;
        }

        public int find(int x){return id[x];}

        public void union(int p,int q){
            int pid=find(p); int qid = find(q);
            if(pid==qid) return;
            for(int i=0;i<id.length;i++){
                if(id[i]==pid) id[i]=qid;
                count--;
            }
        }

        public boolean isConnected(int p, int q){return id[p]==id[q];}
    }

    static class MinPQ{
        Comparable[] pq; int N;

        public MinPQ() {
            pq =new Comparable[2];
            N=0;
        }

        public boolean isEmpty(){return N==0;}

        public void insert(Comparable c){
            if(N==pq.length-1) resize(2*pq.length);
            pq[++N]=c;
            swim(N);
        }

        public Comparable delMin(){
            swap(1, N);
            Comparable min = pq[N--];
            sink(1);
            pq[N+1]=null;
            if(N>0 && N==(pq.length-1)/4) resize(pq.length/2);
            return min;
        }

        private void sink(int parent) {
            while(2*parent<=N){
                int child = 2*parent;
                if(child <N && isGreater(child, child+1)) child++;
                if(!isGreater(parent, child)) break;
                swap(parent, child);
                parent = child;
            }
        }

        private void swim(int child) {
            while(child>1 && isGreater(child/2, child)){
                swap(child, child/2);
                child = child/2;
            }
        }

        private void swap(int parent, int child) {
            Comparable temp = pq[parent];
            pq[parent]=pq[child];
            pq[child]=temp;
        }

        private boolean isGreater(int parent, int child) {
            return pq[parent].compareTo(pq[child]) > 0;
        }

        private void resize(int cap){
            Comparable[] temp =new Comparable[cap];
            for(int i=0;i<=N;i++) temp[i] = pq[i];
            pq = temp;
        }
    }

    static class Edge implements Comparable<Edge>{
        int w,v; double weight;

        public Edge(int w, int v, double weight) {
            this.w = w;
            this.v = v;
            this.weight = weight;
        }

        public int either(){return v;}

        public int other(int x){
            if(x==v) return w;
            else if(x==w) return v;
            else return -1;
        }

        @Override
        public int compareTo(Edge o) {
            if(weight<o.weight) return -1;
            else if (weight>o.weight) return 1;
            return 0;
        }
    }
}
