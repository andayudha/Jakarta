package data_structure;

import java.util.Arrays;

/**
 * Created by anda on 2/20/2016.
 */
public class ListTest {

    public static void main(String[] args){
        XList list = new XList();
        list.add(1); list.add(2); list.add(3);
        list.print();
        System.out.println(list.contains(2));
        list.remove(2); list.print();
        list.add(4);list.add(5);list.add(6); list.print();
        list.remove(1);list.print();
        System.out.println(list.size());
        list.remove(6); list.print();
        list.remove(3);list.remove(4);list.remove(5);
        list.print();

//        list.put(1); list.put(2); list.put(3);list.put(4);
//        list.print();
    }

    static class MList{
        Object[] list;
        int size;
        int last=-1;

        public MList(){
            this(3);
        }

        public MList(int size) {
            this.size = size;
            list = new Object[size];
        }

        public void add(Object o){
            if(last< list.length-1){
                last++;
                list[last]=o;
            }else{//resize
                Object[] baru = new Object[list.length*2];
                System.arraycopy(list, 0, baru, 0, list.length);
                list = baru;
                last++;
                list[last]=o;
                size = list.length;
            }
        }

        public void remove(Object o){
            removeIndex(indexOf(o));
        }

        public void removeIndex(int index){
            Object[] baru = new Object[size];
            if(index<0) return;
            if(index==last){
//                System.arraycopy(list, 0, baru, 0,  last);
            }else if(index==0){
//                System.arraycopy(list, 1, baru, 0, last);
                System.arraycopy(list, 1, list, 0,  last);
            }else{
//                System.arraycopy(list, 0, baru, 0, index);
//                System.arraycopy(list, index+1, baru, index, size-1-index);
                System.arraycopy(list, index+1, list, index, size-1-index);
            }
//            list = baru;
            last--;
        }

        public int size(){
            return last+1;
        }

        public void print(){
            Object[] arr = new Object[last+1];
            System.arraycopy(list, 0, arr, 0, last+1);
            System.out.println(Arrays.toString(arr));
        }

        public int indexOf(Object o){
            for(int i=0;i<=last;i++) if(o.equals(list[i])) return i;
            return -1;
        }

        public boolean contains(Object o){
            return indexOf(o)>=0;
        }
    }

    static class TList{
        Object[] list;
        int size;
        int last=-1;

        public TList(){
            this(5);
        }

        public TList(int size) {
            this.size = size;
            list = new Object[size];
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
                baru[last]=0;
            }
            size = list.length;
        }

        public void remove(Object o){
            removeIndex(indexOf(o));
        }

        public void removeIndex(int index){
            if(index<0) return;
            if(index==0){
                System.arraycopy(list, 1, list, 0, last);
            }else if(index>0 && index<last){
                System.arraycopy(list, index+1, list, index, size-1-index);
            }
            last--;
        }

        public int size(){return last+1;}

        public int indexOf(Object o){
            for(int i=0;i<=last;i++) if(o.equals(list[i])) return i;
            return -1;
        }
        public boolean contains(Object o){
            return indexOf(o)>=0;
        }

        public void print(){
            Object[] arr = new Object[last+1];
            System.arraycopy(list, 0, arr, 0, last+1);
            System.out.println(Arrays.toString(arr));
        }
    }

    static class XList{
        int len;
        Object[] list;
        int last=-1;

        public XList() {
            this(1);
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
            System.arraycopy(list, 0, arr, 0, last+1);
            System.out.println(Arrays.toString(arr));
        }
    }
}
