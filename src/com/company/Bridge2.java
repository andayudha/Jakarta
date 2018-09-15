package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by SRIN on 8/7/2015.
 */
public class Bridge2 {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("bridge2.txt"));
//        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 0; test_case < T; test_case++) {
            String Answer = "";
            int N = sc.nextInt();
            MyGraph g = new MyGraph(N, false);
            for(int i=0;i<N;i++){
                int u = sc.nextInt();
                int t = sc.nextInt();
                for(int j=0;j<t;j++){
                    int v = sc.nextInt();
                    g.addEdge(u,v);
                }
            }
//            g.print();
            CC cc = new CC(g);
            List<MyGraph> subs = cc.getSubGraphs();
            //int x=1;
            System.out.println("#"+(test_case+1)+" "+Answer);
            int c = cc.getSumComponents();
            //System.out.println("number of components : "+c);
            List<Integer[]> criticals = checkBridge3(subs);
            System.out.println(criticals.size()+ " critical links");
            if(criticals.size()>0){
                for(Integer[] link : criticals){
                    System.out.println(Arrays.toString(link));
                }
            }

        }

    }

    static List<Integer[]> checkBridge(MyGraph g, int c){
        List<Integer[]> criticals = new ArrayList<Integer[]>();
        for(int i=0;i<g.N-1;i++){
            for(int j=i+1;j<g.N;j++){
                if(g.A[i][j]==1){
                    g.deleteEdge(i,j);
                    CC tempCC = new CC(g);
                    g.addEdge(i,j);
                    if(tempCC.getSumComponents()>c){
                        criticals.add(new Integer[]{i, j});
                    }
                }
            }

        }
        return criticals;
    }

    static List<Integer[]> checkBridge3(List<MyGraph> subs){
        List<Integer[]> criticals = new ArrayList<Integer[]>();
        for(MyGraph g : subs){
            if(g.a.size()==2){
                criticals.add(new Integer[]{g.a.get(0), g.a.get(1)});
            }else{
                CC cc = new CC(g);
                int c = cc.components;
                for(int i=0;i<g.a.size()-1;i++){
                    for(int j=i+1;j<g.a.size();j++){
                        int u = g.a.get(i);int v = g.a.get(j);
                        if(g.A[u][v]==1){
                            g.deleteEdge(u,v);
                            CC tempCC = new CC(g);
                            g.addEdge(u,v);
                            if(tempCC.getSumComponents()>c){
                                criticals.add(new Integer[]{u, v});
                            }
                        }
                    }

                }
            }

        }
        return criticals;
    }

    static List<Integer[]> checkBridge2(MyGraph g, int c){
        List<Integer[]> criticals = new ArrayList<Integer[]>();
        for(int i=0;i<g.N-1;i++){
            for(int j=i+1;j<g.N;j++){
                if(g.adj[i].contains(j)){
                    g.deleteEdge(i,j);
                    CC tempCC = new CC(g);
                    g.addEdge(i,j);
                    if(tempCC.getSumComponents()>c){
                        criticals.add(new Integer[]{i, j});
                    }
                }
            }

        }

        return criticals;
    }

    static int getUniquepair(int a, int b) {
        return (Math.max(a,b)*(Math.max(a,b)+1)/2)+Math.min(a,b);
    }

     static class MyGraph {
        public int N;
        private List<Integer>[] adj;
        private boolean isDirected;
        int[][] A; List<Integer> a;

        public MyGraph(int n, boolean directed) {
            N = n;
            adj = (List<Integer>[]) new List[N];
            for(int i=0;i<N;i++){
                adj[i] = new ArrayList<Integer>();
            }
            A = new int[N][N];a = new ArrayList<Integer>();
            isDirected=directed;
        }

        public void addEdge(int u, int v){
            adj[u].add(v);
            A[u][v]=1;
            A[v][u]=1;
            if(!a.contains(u)) a.add(u);
            if(!a.contains(v)) a.add(v);
        }

         public void setAllAdj(int u, List<Integer> list){
             adj[u] = list;
             if(!a.contains(u)) a.add(u);
             for(int v : list){
                 A[u][v]=1; A[v][u]=1;
                 if(!a.contains(v)) a.add(v);
             }
         }

        public void deleteEdge(int u, int v){
            adj[u].remove(adj[u].indexOf(v));
            A[u][v]=0;
            if(!isDirected){
                adj[v].remove(adj[v].indexOf(u));
                A[v][u]=0;
            }
        }

        public List<Integer> getNeighbours(int i){
            return adj[i];
        }


        public void print() {
            System.out.println("N = "+N);
            for(int i=0;i<N;i++){
                System.out.println(i + " : " + Arrays.toString(adj[i].toArray()));
            }
//            System.out.print("total edges: "+edges.size()+" | ");
            for(int i=0;i<A.length;i++){
                System.out.println(Arrays.toString(A[i]));
            }
            System.out.println();
        }
    }

    static class CC {
        private boolean[] visited;
        private int[] componentsID;
        private int components;
        private MyGraph parent;
        private List<MyGraph> subGraphs;

        public CC(MyGraph g) {
            components=0;
            parent = g;
            subGraphs = new ArrayList<MyGraph>();
            componentsID =  new int[g.N];
            visited = new boolean[g.N];
            for(int v:g.a){
                if(!visited[v]) {
                    components++;
                    dfs(g, v);
                }
            }
        }

        private void dfs(MyGraph g, int v) {
            visited[v] = true;
            componentsID[v]=components;
            List<Integer> tetangga = g.getNeighbours(v);
            for(int w : tetangga){
                if(!visited[w]){
                    dfs(g, w);
                }
            }
        }

        public int getSumComponents() {
            return components;
        }

        public List<MyGraph> getSubGraphs() {
            boolean[] marked = new boolean[visited.length];
            for(int c=1;c<=components;c++){
                List<Integer> member = new ArrayList<Integer>();
                for(int i=0;i<marked.length;i++){
                    if(!marked[i] && c==componentsID[i]){
                        marked[i]=true;
                        member.add(i);
                    }
                }
                if(member.size()>1){
                    MyGraph g = new MyGraph(parent.N, false);
                    for(int u : member){
                        g.setAllAdj(u, parent.getNeighbours(u));
                    }
                    subGraphs.add(g);
                }
            }
            return subGraphs;
        }
    }



}
