package data_structure;

public class GeekHash<K, V> {

    class HashNode<K, V>
    {
        K key;
        V value;
        HashNode<K, V> next;
        // Constructor
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private static final double LOAD_FACTOR = 0.8;
    private static final int INITIAL_CAPACITY = 32;

    // bucketArray is used to store array of chains
    private HashNode<K, V>[] bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    //num of used bucket
    private int usedBuckets;
    private int maxDepth;

    public GeekHash()
    {
        numBuckets = INITIAL_CAPACITY;
        bucketArray = new HashNode[numBuckets];
        size = 0;  usedBuckets = 0; maxDepth = 0;

        // Create empty chains
//        for (int i = 0; i < numBuckets; i++)
//            bucketArray[i]=null;
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

    // This implements hash function to find index
    private int getBucketIndex(K key)
    {
        int hashCode = stringCode(String.valueOf(key));
        return hashCode >0 ?  hashCode % numBuckets : (-1*hashCode)%numBuckets;
    }

    // Adds a key value pair to hash
    public void put(K key, V value)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray[bucketIndex];
        if (head==null) usedBuckets++;
        int depth = 0;
        // Check if key is already present
        while (head != null)
        {
            if (head.key.equals(key))
            {
                head.value = value;
                return;
            }
            depth ++;
            head = head.next;
        }
        maxDepth = depth>maxDepth ? depth : maxDepth;

        // Insert key in chain
        size++;
        head = bucketArray[bucketIndex];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray[bucketIndex] =  newNode;

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0*size)/numBuckets >= LOAD_FACTOR)
        {
            HashNode<K, V>[] temp = new HashNode[numBuckets];
            //copy
            System.arraycopy(bucketArray, 0, temp, 0, numBuckets);

            numBuckets = 2 * numBuckets;
            bucketArray = new HashNode[numBuckets];
            size = 0;
            usedBuckets = 0;
//            for (int i = 0; i < numBuckets; i++)
//                bucketArray[i] = null;

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

    // Returns value for a key
    public V get(K key)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray[bucketIndex];

        // Search key in chain
        while (head != null)
        {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        // If key not found
        return null;
    }

    // Method to remove a given key
    public V remove(K key)
    {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);

        // Get head of chain
        HashNode<K, V> head = bucketArray[bucketIndex];

        // Search for key in its chain
        HashNode<K, V> prev = null;
        while (head != null)
        {
            // If Key found
            if (head.key.equals(key))
                break;

            // Else keep moving in chain
            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null)
            return null;

        // Reduce size
        size--;

        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray[bucketIndex]= head.next;

        return head.value;
    }
}
