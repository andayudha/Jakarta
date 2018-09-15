package data_structure;

import java.util.Arrays;

/**
 * Created by user on 8/8/2015.
 */
public class EdgeQueue {
    private int size;
    public Edge[] edgeList;

    public EdgeQueue() {
        size=0;
        edgeList = new Edge[size];
    }

    public void add(Edge x){
        size++;
        Edge[] temp = new Edge[size];
        //copy
        for(int i=0;i<size-1;i++){
            temp[i]= edgeList[i];
        }
        temp[size-1]=x;
        edgeList = temp;
    }

    public void addEdge(Edge x){
        size++;
        Edge[] temp = new Edge[size];
        //copy
        for(int i=0;i<size-1;i++){
            temp[i]= edgeList[i];
        }
        temp[size-1]=x;
        edgeList = temp;
        //put new input into sorted size
        if(edgeList.length>1){
            for(int i=size-1;i>0;i--){

            }
        }

    }
    
    public int indexOf(Edge x){
//        if(size>0) {
            for (int i = 0; i < size; i++) {
                if (edgeList[i].equals(x)) return i;
            }
//        }
        return -1;
    }

    public boolean contains(Edge x){
        return (indexOf(x)!=-1);
    }

    public void remove(int index){
        if(size>0 && index!=-1){
            int newSize=size-1;
            Edge[] temp = new Edge[newSize];
            if(index==size-1){
                //copy
                for(int i=0;i<size-1;i++){
                    temp[i]= edgeList[i];
                }
            }else if(index==0){
                for(int i=1;i<size;i++){
                    temp[i-1]= edgeList[i];
                }
            }else{
                for(int i=0;i<index;i++){
                    temp[i]= edgeList[i];
                }
                for(int i=index+1;i<size;i++){
                    temp[i-1]= edgeList[i];
                }
            }
            size--;
            edgeList = temp;
        }

    }

    public int size() {
        return size;
    }

    public void print(){
        System.out.println(Arrays.toString(edgeList));
    }

    public void printEdge(){
        for(Edge o : edgeList){
            Edge e  = (Edge) o;
            System.out.print("("+e.from()+","+e.to()+")->"+e.weight+"   ");
        }
        System.out.println();
    }

    public Edge delMin(){
        int minIndex =0;
        Edge minEdge = (Edge) edgeList[0];
        for(int i=1;i<size;i++){
            Edge e = (Edge) edgeList[i];
            if(e.weight < minEdge.weight){
                minEdge = e;
                minIndex=i;
            }
        }
        //remove index min
        remove(minIndex);
        return minEdge;
    }
    
    public static class Edge implements Comparable<Edge>{
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

        @Override
        public int compareTo(Edge e) {
            if      (this.weight() < e.weight()) return -1;
            else if (this.weight() > e.weight()) return +1;
            else                                 return  0;
        }
    }
}