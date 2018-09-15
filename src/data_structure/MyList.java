package data_structure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 8/8/2015.
 */
public class MyList {
    private int size;
    public Object[] list;

    public MyList() {
        size=0;
        list = new Object[size];
    }

    public void add(Object x){
        size++;
        Object[] temp = new Object[size];
        //copy
        for(int i=0;i<size-1;i++){
            temp[i]=list[i];
        }
        temp[size-1]=x;
        list = temp;
    }

    public int indexOf(Object x){
//        if(size>0) {
            for (int i = 0; i < size; i++) {
                if (list[i].equals(x)) return i;
            }
//        }
        return -1;
    }

    public boolean contains(Object x){
        return (indexOf(x)!=-1);
    }

    public void remove(int index){
        if(size>0 && index!=-1){
            int newSize=size-1;
            Object[] temp = new Object[newSize];
            if(index==size-1){
                //copy
                for(int i=0;i<size-1;i++){
                    temp[i]=list[i];
                }
            }else if(index==0){
                for(int i=1;i<size;i++){
                    temp[i-1]=list[i];
                }
            }else{
                for(int i=0;i<index;i++){
                    temp[i]=list[i];
                }
                for(int i=index+1;i<size;i++){
                    temp[i-1]=list[i];
                }
            }
            size--;
            list = temp;
        }

    }

    public int size() {
        return size;
    }

    public void print(){
        System.out.println(Arrays.toString(list));
    }

    public void printEdge(){
        for(Object o : list){
            Edge e  = (Edge) o;
            System.out.print("("+e.from()+","+e.to()+")->"+e.weight+"   ");
        }
        System.out.println();
    }

    public Edge delMin(){
        int minIndex =0;
        Edge minEdge = (Edge) list[0];
        for(int i=1;i<size;i++){
            Edge e = (Edge) list[i];
            if(e.weight < minEdge.weight){
                minEdge = e;
                minIndex=i;
            }
        }
        //remove index min
        remove(minIndex);
        return minEdge;
    }

    public static class Edge{
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
}