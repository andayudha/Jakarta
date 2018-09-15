package data_structure;

/**
 * Created by anda on 2/21/2016.
 */
public class XList {
    int len;
    Object[] list;
    int last=-1;

    public XList() {
        this(5);
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
}
