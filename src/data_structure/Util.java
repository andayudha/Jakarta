package data_structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by user on 8/8/2015.
 */
public class Util {

    public static void main(String[] args){
//        try {
//            Scanner sc = new Scanner(new File("tinny.txt"));
////            Scanner sc = new Scanner(System.in);
//            int T = sc.nextInt();
//            for(int test_case = 0; test_case < T; test_case++) {
//                int N = sc.nextInt();
//                int E = sc.nextInt();
////                MyList g = new MyList();
//                MinPQ pq = new MinPQ();
//                for(int i=0;i<E;i++){
//                    int u = sc.nextInt();
//                    int v = sc.nextInt();
//                    int w = sc.nextInt();
//                    pq.insert(new EdgeQueue.Edge(u, v, w));
//                }
//                /*g.printEdge();
//                System.out.println();
//                *//*MyList.Edge e = g.delMin();
//                System.out.println("("+e.from()+","+e.to()+")->"+e.weight()+"   ");
//                g.printEdge();*//*
//                while(g.list.length!=0){
//                    MyList.Edge e = g.delMin();
//                    System.out.println("("+e.from()+","+e.to()+")->"+e.weight()+"   ");
//                    g.printEdge();
//                }*/
//                EdgeQueue.Edge e = (EdgeQueue.Edge) pq.delMin();
//                System.out.println("("+e.from()+","+e.to()+")->"+e.weight()+"   ");
//                pq.printEdge();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        List<List<Integer>> combi = combi(new int[]{1,2,3,4}, 2);
        AndaList combi = combin(new int[]{1, 2, 3, 4,5}, 3);
        for(Object a : combi.list){
            AndaList list = (AndaList) a;
            System.out.print(Arrays.toString(list.list));
        }

    }

    public static void quickSort(int[] a, int low, int high){
        if(low>=high) return;
        //center index as pivot
        int middle = low+(high-low)/2;
        int pivot = a[middle];

        //partition
        int i= low; int j=high; //index
        while(i<=j){
            //left
            while(a[i]<pivot){
                i++;
            }
            //right
            while(a[j]>pivot){
                j--;
            }
            if(i<=j){
                //swap
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }

        }//end of while
        System.out.println("pivot : "+pivot);
        System.out.println(Arrays.toString(a));
        //do recursive for two partiotions
        if(low<j) quickSort(a, low, j);
        if(high>i) quickSort(a, i, high);
    }

    public static int gcd(int a, int b){
        if(b==0) {
            return a;
        }else{
            return gcd(b, a%b);
        }

    }

    public static boolean isPrime(int x){
        if(x%2==0) return false;
        for(int i=3; i*i<=x; i+=2){
            if(x%i==0)return false;
        }
        return true;
    }

    public static int lcm(int a,int b){
        return a*b/gcd(a,b);
    }

    public static List<List<Integer>> combi(int[] a, int r){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int length = a.length;
        int count = (int)Math.pow(2, length);
        for(int i=0;i<count;i++){
            List<Integer> sublist = new ArrayList<Integer>();
            for(int j=0;j<length;j++){
                int pos = 1<<j;
                if((i&pos) == pos){
                    sublist.add(a[j]);
                }
            }
            if(sublist.size()==r){
                list.add(sublist);
            }
        }
        return list;
    }

    public static AndaList combin(int[] a, int r){
        AndaList list = new AndaList();
        int length = a.length;
        int count = (int)Math.pow(2, length);
        for(int i=0;i<count;i++){
            AndaList sublist = new AndaList();
            for(int j=0;j<length;j++){
                int pos = 1<<j;
                if((i&pos) == pos){
                    sublist.add(a[j]);
                }
            }
            if(sublist.size()==r){
                list.add(sublist);
            }
        }
        return list;
    }

    public static AndaList<AndaList<Integer>> combine(int[] a, int r){
        AndaList<AndaList<Integer>> list = new AndaList<AndaList<Integer>>();
        int length = a.length;
        int count = (int)Math.pow(2, length);
        for(int i=0;i<count;i++){
            AndaList sublist = new AndaList<Integer>();
            for(int j=0;j<length;j++){
                int pos = 1<<j;
                if((i&pos) == pos){
                    sublist.add(a[j]);
                }
            }
            if(sublist.size()==r){
                list.add(sublist);
            }
        }
        return list;
    }

    public static int pairID(int a, int b){
        return (Math.max(a,b)*(Math.max(a,b)+1)/2)+Math.min(a,b);
    }

    static class AndaList<T>{
        T[] list;
        int size;

        public AndaList() {
            size=0;
            list = (T[]) new Object[size];
        }

        void add(T x){
            size++;
            T[] tem =(T[]) new Object[size];
            for(int i=0;i<size-1;i++){
                tem[i]=list[i];
            }
            tem[size-1]=x;
            list = tem;
        }

        int indexOf(T o){
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
