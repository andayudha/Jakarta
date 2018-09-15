package practice;

import java.util.Arrays;

/**
 * Created by anda on 3/9/2016.
 */
public class PQTest {

    public static void main(String[] args){
        MaxPQ maxPQ = new MaxPQ();
        maxPQ.insert(4);maxPQ.insert(10);maxPQ.insert(35);maxPQ.insert(47);maxPQ.insert(12);maxPQ.insert(27);
        System.out.println(Arrays.toString(maxPQ.pq));

        System.out.println("del max : " + maxPQ.delMax());
        System.out.println(Arrays.toString(maxPQ.pq));

        maxPQ.insert(13);
        System.out.println(Arrays.toString(maxPQ.pq));

        while(!maxPQ.isEmpty()){
            System.out.println("del max : "+maxPQ.delMax());
            System.out.println(Arrays.toString(maxPQ.pq));
        }

        System.out.println("======================================");

        MinPQ minPQ = new MinPQ();
        minPQ.insert(4);minPQ.insert(10);minPQ.insert(35);minPQ.insert(47);minPQ.insert(12);minPQ.insert(27);
        System.out.println(Arrays.toString(minPQ.pq));
        while(!minPQ.isEmpty()){
            System.out.println("del min : "+minPQ.delMin());
            System.out.println(Arrays.toString(minPQ.pq));
        }

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

    static class MaxPQ{
        Comparable[] pq; int N;

        public MaxPQ() {
            pq =new Comparable[2];
            N=0;
        }

        public boolean isEmpty(){return N==0;}

        public void insert(Comparable c){
            if(N==pq.length-1) resize(2*pq.length);
            pq[++N]=c;
            swim(N);
        }

        public Comparable delMax(){
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
                if(child <N && isSmaller(child, child + 1)) child++;
                if(!isSmaller(parent, child)) break;
                swap(parent, child);
                parent = child;
            }
        }

        private void swim(int child) {
            while(child>1 && isSmaller(child / 2, child)){
                swap(child, child/2);
                child = child/2;
            }
        }

        private void swap(int parent, int child) {
            Comparable temp = pq[parent];
            pq[parent]=pq[child];
            pq[child]=temp;
        }

        private boolean isSmaller(int parent, int child) {
            return pq[parent].compareTo(pq[child]) < 0;
        }

        private void resize(int cap){
            Comparable[] temp =new Comparable[cap];
            for(int i=0;i<=N;i++) temp[i] = pq[i];
            pq = temp;
        }
    }
}
