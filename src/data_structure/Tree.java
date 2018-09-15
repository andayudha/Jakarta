package data_structure;

/**
 * Created by user on 8/23/2015.
 */
public class Tree {

    static class BasicTree{
        Object[] array;

        public BasicTree () {
            array = new Object [128];
        }

        public boolean empty () {
            return (array[1] == null);
        }

        public int left (int i) {  return 2*i;  }
        public int right (int i) {  return 2*i + 1;  }
        public int parent (int i) {  return i/2;  }

        private Object getNode(int i){
            if (i < 0 || i >= array.length) return null;
            return array[i];
        }

        private void setNode (int i, Object obj) {
            if (i < 0 || i >= array.length) return;
            array[i] = obj;
        }

        public void setLeft(int parent, Object o){
            setNode(left(parent), o);
        }

        public void setRight(int parent, Object o){
            setNode(right(parent), o);
        }

        public void add(Object o){

        }

    }
}
