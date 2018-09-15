package practice;

import data_structure.MyTree;

import java.util.Arrays;

/**
 * Created by anda on 3/9/2016.
 */
public class TreeTest {

    public static void main(String[] args){
        MyTree tree = new MyTree();
        tree.insert(4, 1);
        tree.insert(2, 2);
        tree.insert(6, 3);
        tree.insert(3, 4);
        tree.insert(5, 5);
        tree.insert(1, 6);
        tree.insert(7, 7);

        System.out.println(tree.root);
        System.out.println(tree.maxDepth());

        int levvel = tree.getLevel(4);
        MyTree.Siblings siblings = new MyTree.Siblings(tree,levvel , 1000);
        System.out.println(Arrays.toString(siblings.siblings.list));
        System.out.println(tree.minValue());
        System.out.println(tree.maxValue());
//        tree.delete(6);System.out.println(tree.root);
        tree.delete(4);System.out.println(tree.root);
//        tree.delete(4);System.out.println(tree.root);
    }
}
