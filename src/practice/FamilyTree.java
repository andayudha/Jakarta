package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 10/11/2015.
 */
public class FamilyTree {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("family.txt"));
            int T = sc.nextInt();
            for (int tc = 0; tc < T; tc++) {
                int N = sc.nextInt();
                Tree tree = new Tree(N + 1);
                for (int i = 0; i < N; i++) {

                    int parent = sc.nextInt();
                    int child = sc.nextInt();
                    tree.add(child, parent);
                }
                System.out.println(Arrays.toString(tree.levels));
                System.out.println("Case #"+(tc+1)+" : ");
                System.out.println("Eldest : "+tree.getEldest());
                String s;
                int[] sibing = tree.getSiblings();
                if(sibing.length==0) s = "none";
                else s = Arrays.toString(sibing).replace(",", "").replace("[","").replace("]","");
                System.out.println("SameGen : " + s);

                int[] young = tree.getYoungss();
                if(young.length==0) s = "none";
                else s = Arrays.toString(young).replace(",", "").replace("[","").replace("]","");
                System.out.println("Younglings : "+s);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Tree{
        int N;
        UnionFind uf;
        int[] levels;

        public Tree(int n) {
            this.N = n;
            uf = new UnionFind(n);
            levels = new int[N];
        }

        public void add(int parent, int child) {

            //update levels
            int childId = uf.find(child);
            int parentLevel = levels[parent];
            int x=0;
            for(int i=0;i<N;i++){
                if(uf.id[i]==childId && i!=parent){
                    int oldRootLevel = levels[uf.find(i)];
                    if(levels[i]>parentLevel){
                        levels[i]=parentLevel;
                    }else if(oldRootLevel<0){
                        levels[i]+=parentLevel;
                    }
                    levels[i]--;
                }
            }
            uf.union(child, parent);
        }


        public int getEldest(){
            for(int i=0;i<N;i++){
                if(levels[i]==0) return  i;
            }
            return -1;
        }

        public int[] getSiblings(){
            int level = levels[0];
            int size=0;
            int[] a = new int[size];
            for(int i=1;i<N;i++){
                if(levels[i]==level){
                    size++;
                    int[] temp = new int[size];
                    for(int j=0;j<size-1;j++) temp[j]=a[j];
                    temp[size-1]=i;
                    a=temp;
                }
            }
            Arrays.sort(a);
            return a;
        }

        public int[] getYoungss(){
            int level = 1;
            for(int i=1;i<N;i++){
                level = Math.min(level, levels[i]);
            }
            int size=0;
            int[] a = new int[size];
            for(int i=1;i<N;i++){
                if(levels[i]==level){
                    size++;
                    int[] temp = new int[size];
                    for(int j=0;j<size-1;j++) temp[j]=a[j];
                    temp[size-1]=i;
                    a=temp;
                }
            }
            Arrays.sort(a);
            return a;
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
}
