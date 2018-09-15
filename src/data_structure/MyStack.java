package data_structure;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by user on 8/8/2015.
 */
public class MyStack {
    private int top;
    private Object[] storage;

    public MyStack(int capacity) {
        storage = new Object[capacity];
        top = -1;
    }

    public void push(int value) {
        top++;
        storage[top] = value;
    }

    public Object peek() {
        return storage[top];
    }

    public Object pop() {
        Object x= storage[top];
        top--;
        return x;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean contains(Object x){
        for(int i=0;i<=top;i++){
            if(storage[i].equals(x)) return true;
        }
        return false;
    }

    public void print(){
        System.out.println(Arrays.toString(storage));
//        for(int i=0;i<=top;i++){
//            System.out.print(storage[i]+", ");
//        }
        System.out.println();
    }

    public static void main(String[] args){
        MStack stack = new MStack((5));
        stack.push(1);        stack.push(2);        stack.push(3);
        stack.print();
        System.out.println("peek "+stack.peek());
        System.out.println("pop "+stack.pop());
        stack.print();
        stack.push(4); stack.print();
    }


    static class MStack{
        int n;
        int[] arr;
        int top;

        public MStack( int size) {
            top = -1;
            this.n = size;
            arr = new int[size];
        }

        public void push(int x){
            top++;
            arr[top] = x;
        }

        public int pop(){
            int p = arr[top];
            top--;
            return p;
        }

        public boolean isEmpty(){
            return top==-1;
        }

        public int peek(){
            if (isEmpty()) return -1;
            return arr[top];
        }

        public void print(){
            if(isEmpty()) System.out.println("empty stack");
            else{
                int[] p = Arrays.copyOfRange(arr, 0, top+1);
                System.out.println(Arrays.toString(p));
            }
        }
    }
}
