package data_structure;

/**
 * Created by anda on 10/11/2015.
 */
public class MyTree {
    static class Node {
        Comparable val;
        int key, N;
        Node left, right;

        public Node(Comparable val, int key, int n) {
            this.val = val;
            this.key = key;
            N = n;
        }

        @Override
        public String toString() {
            return "("+left+"["+val+"]"+right+")";
        }
    }

    public MyTree() {
    }

    ;

    public Node root = null;

    private Node insert(Node parent, Comparable value, int key) {
        if (parent == null) {
            return new Node(value, key, 1);
        } else {
            if (value.compareTo(parent.val) < 0) {
                parent.left = insert(parent.left, value, key);
            } else {
                parent.right = insert(parent.right, value, key);
            }
            parent.N = size(parent.left) + size(parent.right) + 1;
        }
        return parent;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Comparable minValue(){
        return min(root).val;
    }


    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Comparable maxValue(){
        return max(root).val;
    }


    public void insert(Comparable val, int key) {
        root = insert(root, val, key);
    }

    private int getKey(Node parent, Comparable val) {
        if (parent == null) return -1;
        if (val.compareTo(parent.val) < 0) {
            return getKey(parent.left, val);
        } else if (val.compareTo(parent.val) > 0) {
            return getKey(parent.right, val);
        } else return parent.key;
    }

    private int getLevel(Node parent, Comparable val, int level) {
        if (parent == null) return 1;
        if (val.compareTo(parent.val) < 0) {
            return getLevel(parent.left, val, level + 1);
        } else if (val.compareTo(parent.val) > 0) {
            return getLevel(parent.right, val, level + 1);
        } else return level;
    }

    public int getLevel(Comparable val) {
        return getLevel(root, val, 1);
    }

    public int search(Comparable val) {
        return getKey(root, val);
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public int maxDepth() {
        return (maxDepth(root));
    }

    private int maxDepth(Node node) {
        if (node == null) {
            return (0);
        } else {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
            return (Math.max(lDepth, rDepth) + 1);
        }
    }

    private Comparable getParent(Node parent, Comparable p, Comparable val) {
        if (parent == null) return -1;
        if (val.compareTo(parent.val) < 0)
            return getParent(parent.left, parent.val, val);
        else if (val.compareTo(parent.val) > 0)
            return getParent(parent.right, parent.val, val);
        else return p;
    }

    public void delete(Comparable key){
        root = delete(root, key);
    }

    private Node delete(Node parrent, Comparable val)  {
        if (parrent == null) return null;
        int cmp = val.compareTo(parrent.val);
        if (cmp < 0) parrent.left = delete(parrent.left, val);
        else if (cmp > 0) parrent.right = delete(parrent.right, val);
        else
        {
            if (parrent.right == null) return parrent.left;
            if (parrent.left == null) return parrent.right;
            Node t = parrent;
            parrent = min(t.right); // See page 407.
            parrent.right = deleteMin(t.right);
            parrent.left = t.left;
        }
        parrent.N = size(parrent.left) + size(parrent.right) + 1;
        return parrent;
    }

    public void deleteMin()
    {
        root = deleteMin(root);
    }
    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static class Siblings{
        MyTree tree; int level;public MyList siblings;int pivot;
        public Siblings(MyTree tree,int level,int pivot) {
            this.tree = tree; siblings = new MyList();
            this.level = level;this.pivot = pivot;
            findSiblings();
        }
        public void findSiblings() {
            Node[] path = new Node[1000];
            findSiblings(tree.root, path, 0);
        }
        private void findSiblings(Node node, Node[] path, int
                pathLen) {
            if (node==null || pathLen>level) return;
            path[pathLen] = node;
            pathLen++;
            if (node.left==null && node.right==null) {
                Node obj = path[level-1];
                if(path[level-1]!=null &&
                        !siblings.contains(obj) && obj.key!=pivot){
                    siblings.add(path[level-1]);
                }
            } else {
                findSiblings(node.left, path, pathLen);
                findSiblings(node.right, path, pathLen);
            }
        }
    }
}
