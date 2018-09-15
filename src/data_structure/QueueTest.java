package data_structure;

import java.util.Arrays;

/**
 * Created by anda on 2/25/2016.
 */
public class QueueTest {

    public static void main(String[] args){
        MQueue queue = new MQueue(10);
        queue.enqueue(1);queue.enqueue(2);queue.enqueue(3);queue.enqueue(4); queue.print();
        System.out.println("front : " + queue.getFirst() + "; size: " + queue.size());
        System.out.println("deq : "+queue.dequeue()+"; size: "+queue.size());
        queue.print();
        while (!queue.isEmpty()){
            queue.dequeue();
            queue.print();
        }
        queue.enqueue(1);queue.enqueue(2);queue.enqueue(3);queue.enqueue(4); queue.print();
        System.out.println("front : " + queue.getFirst() + "; size: " + queue.size());
        System.out.println("deq : " + queue.dequeue() + "; size: " + queue.size());
        queue.print();
    }

    static class MQueue{
        int last, first, capacity;
        Object[] q;

        public MQueue(int size) {
            this.capacity = size;
            q = new Object[size];
            last = -1; first = 0;
        }

        public void enqueue(Object o){
            if(last+1>=capacity) return;
            last++;
            q[last]=o;
        }

        public Object dequeue() {
            if (isEmpty()) return null;
            Object o = q[first];
            first++;
            return o;
        }

        public Object getFirst(){
            if(isEmpty()) return null;
            return q[first];
        }

        public boolean isEmpty(){
            return last<first;
        }

        public int size(){
            if(isEmpty()) return 0;
            return last-first+1;
        }

        public void print(){
            Object[] temp = new Object[size()];
            System.arraycopy(q, first, temp, 0, size());
            System.out.println(Arrays.toString(temp));
        }
    }
}
