
package data_structure;

public class TrollMap<K,V> {

    private int TABLE_SIZE=8;
    private int numBuckets;
    private HashNode<K, V>[] bucketArray;

    // Current capacity of array list

    // Current size of array list
    private int size;

    public TrollMap()
    {
        bucketArray = new HashNode[TABLE_SIZE];
        numBuckets = 0;
        size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
            bucketArray[i]=null;
    }

    private int calculateIndex(K key)
    {
        int h = 0;
        h ^= key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        int hash = h ^ (h >>> 7) ^ (h >>> 4);
        return hash & (TABLE_SIZE-1);
    }

    public void put(K key, V value)
    {
        // Find head of chain for given key
        int bucketIndex = calculateIndex(key);
        HashNode<K, V> head = bucketArray[bucketIndex];
        if (head==null) numBuckets++;
        // Check if key is already present
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert key in chain
        size++;
        head = bucketArray[bucketIndex];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray[bucketIndex] = newNode;

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0*size)/TABLE_SIZE >= 0.8)
        {
            HashNode<K, V>[] temp = new HashNode[TABLE_SIZE];
            //copy
            System.arraycopy(bucketArray, 0, temp, 0, size);

            TABLE_SIZE = 2 * TABLE_SIZE;
            bucketArray = new HashNode[TABLE_SIZE];
            size = 0;
            for (int i = 0; i < TABLE_SIZE; i++)
                bucketArray[i]=null;

            for (HashNode<K, V> headNode : temp)
            {
                while (headNode != null)
                {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public V get(K key){
        int index = calculateIndex(key);
        HashNode<K,V> head = bucketArray[index];
        while (head!=null){
            if(head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    private int stringCode(String key){
        int h = 0;
        if (h == 0 && key.length() > 0) {
            for (int i = 0; i < key.length(); i++) {
                h = 31 * h + key.charAt(i);
            }
        }
        return h;
    }

    class HashNode<K, V>
    {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }

}
