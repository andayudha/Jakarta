package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by SRIN on 8/20/2015.
 */
public class FlowMST {
    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("tinny.txt"));
//            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();
            for(int test_case = 0; test_case < T; test_case++) {
                int N = sc.nextInt();
                int E = sc.nextInt();
                WGraph g = new WGraph(N);
                for(int i=0;i<E;i++){
                    int u = sc.nextInt();
                    int v = sc.nextInt();
                    int w = sc.nextInt();
                    g.addEdge(new Edge(u, v, w));
                }

                LazyMST prim = new LazyMST(g);
                prim.printEdge();
                System.out.println("V = "+g.V+" mst size : "+prim.mst.size+" total : "+prim.total);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class LazyMST{
        private boolean[] visited;
        private AndaList mst;
        private MinPQ pq;
        int total=0;

        public LazyMST(WGraph g) {
            pq = new MinPQ();
            visited = new boolean[g.V];
            mst = new AndaList();
            visit(g,0);
            while (!pq.isEmpty()){
                Edge e = (Edge)pq.delMin();
                //System.out.println("pop (" + e.from() + "," + e.to() + ")->" + e.weight() + "   ");
               // pq.printEdge();
                int v = e.either(), w = e.other(v); //   edge from p
                if(visited[v] && visited[w]) continue;
                mst.add(e);
                total+=e.weight();
                if(!visited[v]) visit(g,v);
                if(!visited[w]) visit(g,w);
            }
        }

        private void visit(WGraph g, int v) {
            visited[v]=true;
            AndaList adj = g.adj(v);
            for(Object o : adj.list){
                Edge e = (Edge) o;
                if(!visited[e.other(v)]){
                    pq.insert(e);
                }
            }
           // pq.printEdge();
        }

        public void printEdge(){
            for(Object o : mst.list){
                Edge e = (Edge) o;
                System.out.print("(" + e.from() + "," + e.to() + ")->" + e.weight() + "   ");
            }
            System.out.println();
        }
    }

    static class WGraph{
        private int V;               // number of vertices
        private int E;                     // number of edges
        private AndaList[] adj;
        public WGraph(int V)
        {
            this.V = V;
            this.E = 0;
            adj = new AndaList[V];
            for (int v = 0; v < V; v++){
                adj[v] = new AndaList();
            }
        }

        public void addEdge(Edge e)
        {
            adj[e.from()].add(e);
            adj[e.to()].add(new Edge(e.to(), e.from(), e.weight()));
            E++;
        }

        public AndaList adj(int v){
            return adj[v];
        }

        public void resizeGraph(int newSize){
            if(newSize>V){
                AndaList[] temp = new AndaList[newSize];
                for(int i=0;i<V;i++){
                    temp[i]=adj[i];
                }
                adj=temp;
                V = newSize;
            }

        }
    }

    static class Edge implements Comparable<Edge>{
        private final int v;                       // edge source
        private final int w;                       // edge target
        private final int weight;               // edge weight
        public Edge(int v, int w, int weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        public int weight()
        {  return weight;  }
        public int from()
        {  return v;  }
        public int to()
        {  return w;  }
        public int either()
        { return v; }
        public int other(int vertex)
        {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new RuntimeException("Inconsistent edge");
        }
        @Override
        public int compareTo(Edge e) {
            if      (this.weight() < e.weight()) return -1;
            else if (this.weight() > e.weight()) return +1;
            else                                 return  0;
        }
    }

    static class MinPQ<Key> {
        private Key[] pq;                    // store items at indices 1 to N
        private int N;                       // number of items on priority queue

        public MinPQ() {
            pq = (Key[]) new Object[2];
            N = 0;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public Key min() {
            if (isEmpty()) return null;
            return pq[1];
        }

        // helper function to double the size of the heap array
        private void resize(int capacity) {
            assert capacity > N;
            Key[] temp = (Key[]) new Object[capacity];
            for (int i = 1; i <= N; i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }

        public void insert(Key x) {
            // double size of array if necessary
            if (N == pq.length - 1) resize(2 * pq.length);

            // put x, and percolate it up to maintain heap invariant
            pq[++N] = x;
            swim(N);
            //assert isMinHeap();
        }

        public Key delMin() {
            if (isEmpty()) return null;
            swap(1, N);
            Key min = pq[N--];
            sink(1);
            pq[N+1] = null;         // avoid loitering and help with garbage collection
            if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length  / 2);

            return min;
        }

        private void swim(int k) {
            while (k > 1 && isGreater(k / 2, k)) {
                swap(k, k / 2);
                k = k/2;
            }
        }

        private void sink(int k) {
            while (2*k <= N) {
                int j = 2*k;
                if (j < N && isGreater(j, j + 1)) j++;
                if (!isGreater(k, j)) break;
                swap(k, j);
                k = j;
            }
        }

        private boolean isGreater(int i, int j){
            return ((Comparable) pq[i]).compareTo(pq[j]) >0;
        }

        private void swap(int i, int j) {
            Key swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
        }

        public void printEdge(){
            for(int i=1;i<N;i++){
                Edge e = (Edge) pq[i];
                System.out.print("(" + e.from() + "," + e.to() + ")->" + e.weight() + "   ");
            }
            System.out.println();
        }

    }

    static class AndaList{
        Object[] list;
        int size;

        public AndaList() {
            size=0;
            list = new Object[size];
        }

        void add(Object x){
            size++;
            Object[] tem = new Object[size];
            for(int i=0;i<size-1;i++){
                tem[i]=list[i];
            }
            tem[size-1]=x;
            list = tem;
        }

        int indexOf(Object o){
            for(int i=0;i<size;i++){
                if(list[i].equals(o)) return i;
            }
            return -1;
        }

        public int size() {
            return size;
        }
    }
}
