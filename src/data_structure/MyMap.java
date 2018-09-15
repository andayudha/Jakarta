/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package data_structure;

public class MyMap<K,V> {

    private final static int TABLE_SIZE = 8;

    private HashEntry[] table;

    public MyMap() {
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    private int calculateHashCode(String key) {
        int mod = stringCode(key) % TABLE_SIZE;
        return mod < 0 ? mod + TABLE_SIZE : mod;
    }

    private int findIndex(String key) {
        int index = calculateHashCode(key);
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + 1) % TABLE_SIZE;
        }
        return index;
    }

    public Object get(String key) {
        int index = findIndex(key);
        return table[index] == null ? -1 : table[index].getValue();
    }

    public void put(String key, V value) {
        table[findIndex(key)] = new HashEntry(key, value);
    }

    private int stringCode(String key){
        int h = 0;
        if (h == 0 && key.length() > 0) {
            for (int i = 0; i < key.length(); i++) {
                h = 31 * h + key.charAt(i);
            }
        }
        return h;
    }

    public class HashEntry<K, V>{
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args){
        MyMap map = new MyMap();
        map.put("hello", 10);
        map.put("helli", 8);
        map.put("hella", 9);
        map.put("hellu", 7);
        map.put("helle", 5);
        map.put("hellios", 15);
        System.out.println(map.get("hello"));
//        System.out.println(test.stringCode() +" | "+map.stringCode(test));
    }
}
