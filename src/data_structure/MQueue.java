package data_structure;

/**
 * Created by anda on 2/25/2016.
 */
public class MQueue {
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
}
