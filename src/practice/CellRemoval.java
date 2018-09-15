package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 10/14/2015.
 */
public class CellRemoval {


    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(new File("cellremoval_input.txt"));
            Scanner out = new Scanner(new File("Cellremoval_output.txt"));
            int T = sc.nextInt();
            for(int t=0;t<T;t++){
                int N = sc.nextInt();
                int[] data = new int[N];
                for(int i=0;i<N;i++){
                    int x = sc.nextInt();
                    data[i] = x;
                }
                int del = sc.nextInt();
                Tree tree = new Tree(data);
                tree.remove(del);
                String solution = out.nextLine();
                String result = "Case #"+(t+1)+" "+tree.getTotalMature();
                if(!solution.equals(result)){
                     System.out.println("false, case "+(t+1) +" : "+ Arrays.toString(data));
                }else{
                    System.out.println("true");
                }
                System.out.println("Case #"+(t+1)+" "+tree.getTotalMature());
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    static class Tree{
        int N;
        UnionFind uf;
        boolean[] isParent;
        MyList[] parentsList;
        int[] data;
        int removed;
        boolean[] isRemoved;

        public Tree(int[] data) {
            this.data = data;
            this.N = data.length;
            uf = new UnionFind(N);
            parentsList = new MyList[N];
            for(int i=0;i<N;i++){
                parentsList[i] = new MyList();
            }
            isRemoved = new boolean[N];
            isParent = new boolean[N];
            for(int i=0;i<N;i++){
                add(data[i], i);
            }
        }

        public void add(int parent, int child) {
            if(parent>-1){
                isParent[parent]=true;
                parentsList[child].add(parent);

                for(int p : parentsList[parent].list) parentsList[child].add(p);

                //update allChild
                for(int i=0;i<N;i++){
                    if(parentsList[i].contains(child)){
                        parentsList[i].add(parent);
                        //put all parent parent
                        for(int p : parentsList[parent].list) parentsList[i].add(p);
                    }
                }
                //update parent

//                uf.union(child, parent);
            }else{
                isParent[child]=true;
            }

        }

        public void remove(int key){
            this. removed = key;

            int newId = key; uf.ids[key]=newId;
            isRemoved[key]=true;
            if(!isParent[key]) {
                return;
            }
            isParent[key]=false;
            // change all child id into new id
            for(int i=0;i<N;i++){
                if(i!=key){
                    if(parentsList[i].contains(key)){
//                        uf.ids[i]=newId;
                        isRemoved[i]=true;
                    }
                }
            }
        }

        public int getTotalMature(){
            int sum = 0;
            for(int i=0;i<N;i++){
                if( /*uf.ids[i]!=uf.ids[removed]*/ !isRemoved[i]  && !isParent[i]) sum++;
            }
            return sum;
        }


    }

    static class UnionFind{
        public int[] ids; private int count;
        public UnionFind(int N) {
            count=N;
            ids = new int[N];
            for(int i=0;i<N;i++) ids[i]=i;
        }
        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }
        public void union(int p, int q){
            int pID = find(p); int qID = find(q);
            if(isConnected(pID, qID)) return;
            for(int i=0;i< ids.length;i++)
                if(ids[i]==pID) ids[i]=qID;
            count--;
        }
        public int find(int p) { return ids[p]; }

    }

    public static class MyList {
        int[] list;
        int size;

        public MyList() {
            size = 0;
            list = new int[size];
        }

        void add(int x) {
            size++;
            int[] tem = new int[size];
            for (int i = 0; i < size - 1; i++) {
                tem[i] = list[i];
            }
            tem[size - 1] = x;
            list = tem;
        }
        int indexOf(int o){
            for(int i=0;i<size;i++){
                if(o==list[i]) return i;
            }
            return -1;
        }

        boolean contains(int o){
            return indexOf(o)>-1;
        }

    }
}
