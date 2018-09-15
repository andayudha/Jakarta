package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by SRIN on 8/13/2015.
 */
public class SendingMailShortestPath {

    public static void main(String[] args) {
        TreeSet<Integer> tree = new TreeSet<Integer>();

        try {
            Scanner sc = new Scanner(new File("03SendingEmail_input.txt"));
//            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();
            for(int test_case = 0; test_case < T; test_case++) {
                int N = sc.nextInt();
                int E = sc.nextInt();
                int S = sc.nextInt();
                int t = sc.nextInt();/**/
                WGraph g;
                if(t>=N){
                    g= new WGraph(t+1);
                }else{
                    g= new WGraph(N);
                }
                for(int i=0;i<E;i++){
                    int u = sc.nextInt();
                    int v = sc.nextInt();
                    int max = Math.max(u,v);
                    if(max>g.V){
                        g.resizeGraph(max);
                    }
                    int w = sc.nextInt();

                    g.addEdge(new Edge(u, v, w));
                }

                Djikstra dj = new Djikstra(g, S);
                if(dj.hasPath(t)){
//                    System.out.println(Arrays.toString(dj.pathTo(t).list));
                    System.out.println("#"+(test_case+1)+" "+dj.distTo(t));
                }else{
                    System.out.println("#"+(test_case+1)+" "+"unreachable");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Djikstra{
        private Edge[] edgeTo;
        private int[] distTo;
        private IndexMinPQ<Integer> pq;
        int source;

        public Djikstra(WGraph G, int s) {
            source = s;
            edgeTo = new Edge[G.V];
            distTo = new int[G.V];
            pq = new IndexMinPQ<Integer>(G.V);
            for (int v = 0; v < G.V; v++){
                distTo[v] = Integer.MAX_VALUE;
            }
            distTo[s] = 0;
            pq.insert(s, 0);
            while (!pq.isEmpty()){
                relax(G, pq.delMin());
            }
        }

        private void relax(WGraph G, int v)
        {
            for(Object o : G.adj(v).list)
            {
                Edge e = (Edge) o;
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) {
                        pq.change(w, distTo[w]);
                    }
                    else{
                        pq.insert(w, distTo[w]);
                    }
                }
            }
        }

        AndaList pathTo(int v){
            if(!hasPath(v)) return null;
            AndaList path = new AndaList();
            for(int x=v; x!=source; x=edgeTo[x].from()){
                path.add(x);
            }
            path.add(source);
            return path;
        }

        boolean hasPath(int v){ return  distTo[v]!=Integer.MAX_VALUE;}

        public int distTo(int v) {
            return distTo[v];
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

    static class Edge{
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

        boolean contains(Object o){
            return indexOf(o)>-1;
        }

        public int size() {
            return size;
        }
    }

    static class IndexMinPQ <Key>{
        private int maxN;   private int N;
        private int[] pq;   private int[] qp;
        private Key[] keys;

        public IndexMinPQ(int maxN) {
            if (maxN < 0) throw new IllegalArgumentException();
            this.maxN = maxN;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq = new int[maxN + 1];  qp = new int[maxN + 1];
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public boolean contains(int i) {
            if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
            return qp[i] != -1;
        }

        public int size() {
            return N;
        }

        public void insert(int i, Key key) {
            if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
            N++;
            qp[i] = N;
            pq[N] = i;
            keys[i] = key;
            swim(N);
        }

        public int delMin() {
            if (N == 0) throw new NoSuchElementException("Priority queue underflow");
            int min = pq[1];
            exch(1, N--);
            sink(1);
            qp[min] = -1;            // delete
            keys[pq[N + 1]] = null;    // to help with garbage collection
            pq[N + 1] = -1;            // not needed
            return min;
        }

        @Deprecated
        public void change(int i, Key key) {
            changeKey(i, key);
        }

        public void changeKey(int i, Key key) {
            if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
            if (!contains(i)) return;
            keys[i] = key;
            swim(qp[i]);
            sink(qp[i]);
        }


        private boolean greater(int i, int j) {
            return ((Comparable) keys[pq[i]]).compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
    }
}
