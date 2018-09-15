package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 3/5/2016.
 */
public class ConnectTest {

    public static void main(String[] args){
        try {
//            Scanner sc = new Scanner(new File("graph3.txt"));
//            int T = sc.nextInt();
//            for(int test_case = 0; test_case < T; test_case++) {
//                int Answer;
//                String h = sc.next();sc.nextLine();
//                int N = charToInt(h.charAt(0));
//                MGraph g = new MGraph(N);
//                String line=sc.nextLine();
//
//                while(!line.isEmpty()){
//                    int u = charToInt(line.charAt(0));
//                    int v = charToInt(line.charAt(1));
//                    g.addEdge(u, v);
//                    try {
//                        line = sc.nextLine();
//                    }catch (Exception e){
//                        line = "";
//                    }
//                }
//
//                Connect c = new Connect(g);
//                Answer = c.count;
//                System.out.println("#"+(test_case+1)+" "+Answer);

            Scanner sc = new Scanner(new File("graph5.txt"));
            int T = sc.nextInt();
            for(int test_case = 0; test_case < T; test_case++) {
                int N = sc.nextInt();
                int E = sc.nextInt();
                MGraph g= new MGraph(N);
                CycleUnion cycleUnion = new CycleUnion(N);
                for(int i=0;i<E;i++){
                    int u = sc.nextInt();
                    int v = sc.nextInt();
                    g.addEdge(u,v);
                    cycleUnion.addEdge(u, v);
                }
                System.out.println(Arrays.toString(cycleUnion.edgeTo));
                Cycle c = new Cycle(g);
                if(c.isCyclic){
                    c.cycle.print();
                }else{
                    System.out.println("no cycle");
                }

                System.out.println(cycleUnion.isCyclic);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Cycle{
        MGraph g;
        boolean[] visited;
        int[] edgeTo;
        XList cycle;
        boolean isCyclic;

        public Cycle(MGraph g) {
            this.g = g;
            visited = new boolean[g.N];
            edgeTo = new int[g.N];
            for(int v=1;v<g.N;v++){
                if(!visited[v]){
                    dfs(v,v);
                }
            }
        }

        private void dfs(int v, int u) {
            for(Object o: g.tetangga[v].list){
                if(o!=null){
                    int w = (Integer) o;
                    if(cycle!=null) return;
                    if(!visited[w]){
                        visited[w]=true;
                        edgeTo[w]=v;
                        dfs(w, v);
                    }else if(w!=u){
                        isCyclic = true;
                        cycle = new XList();
                        for(int x=v; x!=w; x=edgeTo[x]){
                            cycle.add(x);
                        }
                        cycle.add(w);
                    }

                }
            }
        }


    }

    static class CycleUnion{
        UnionFind uf;
        int[] edgeTo;
        int N; boolean isCyclic;

        public CycleUnion(int n) {
            N = n+1;
            uf = new UnionFind(N);
            edgeTo = new int[N];
        }

        public void addEdge(int u, int v){
            if(uf.isConnected(u, v)) {
                isCyclic=true;
                edgeTo[v]=u;
                XList cycle = new XList();
                for(int x=v;x!=u;x = edgeTo[x]){
                    cycle.add(x);
                }
                cycle.add(u);
                cycle.print();
            }else{
                uf.union(u, v);
                edgeTo[v]=u;
            }
        }
    }

    static class UnionFind{
        public int[] id; private int count;
        public UnionFind(int N) {
            count=N;id = new int[N];
            for(int i=0;i<N;i++) id[i]=i;
        }
        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }
        public void union(int p, int q){
            int pID = find(p); int qID = find(q);
            if(isConnected(pID, qID)) return;
            for(int i=0;i<id.length;i++)
                if(id[i]==pID) id[i]=qID;
            count--;
        }
        public int find(int p) { return id[p]; }
    }

    static class Connect{
        MGraph graph;
        boolean[] visited;
        int count;

        public Connect(MGraph graph) {
            this.graph = graph;
            visited = new boolean[graph.N];
            for(int v=1;v<graph.N;v++){
                if(!visited[v]){
                    count++;
                    dfs(v);
                }
            }
        }

        private void dfs(int v) {
            for(Object o : graph.tetangga[v].list){
                if(o!=null){
                    int w = (Integer) o;
                    if(!visited[w]){
                        visited[w]=true;
                        dfs(w);
                    }
                }
            }
        }
    }

    static class MGraph{
        int N;
        public XList[] tetangga;

        public MGraph(int n) {
            N = n+1;
            tetangga = new XList[N];
            for(int i=0;i<N;i++) tetangga[i]=new XList();
        }

        public void addEdge(int x, int y){
            tetangga[x].add(y);tetangga[y].add(x);
        }
    }

    static class XList{
        int len;
        Object[] list;
        int last=-1;

        public XList() {
            this(8);
        }

        public XList(int size) {
            this.len = size;
            list = new Object[this.len];
        }

        public void add(Object o){
            if(last<list.length-1){
                last++;
                list[last]=o;
            }else{
                Object[] baru = new Object[list.length*2];
                System.arraycopy(list, 0, baru, 0, list.length);
                list = baru;
                last++;
                list[last]=o;
            }
            len = list.length;
        }

        public void removeIndex(int index){
            if(index<0) return;
            if(index==0) System.arraycopy(list, 1, list, 0, last);
            else if(index>0 && index <last) System.arraycopy(list, index+1, list, index, last-index);
            last--;
        }

        public void remove(Object o){
            removeIndex(indexOf(o));
        }

        public int indexOf(Object o){
            for(int i=0;i<=last;i++) if(o.equals(list[i])) return i;
            return -1;
        }

        public int size(){return last+1;}

        public boolean contains(Object o){return indexOf(o)>=0;}

        public void print(){
            Object[] arr = new Object[last+1];
            System.arraycopy(list, 0, arr, 0, last + 1);
            for(Object o : arr){
                if(o!=null){
                    System.out.print(o+ ",");
                }
            }
            System.out.println();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    static int charToInt(char c){
        int temp = (int)c;
        int temp_integer = 64; //for upper case
        if(temp<=90 & temp>=65){
            return temp-temp_integer;
        }else {
            return 0;
        }
    }
}


