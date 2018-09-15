/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by anda on 3/19/2016.
 */
public class SkyLineProblem {

    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(new File("03_JakartaSkyline_input.txt"));
            Scanner out = new Scanner(new File("03_JakartaSkyline_output.txt"));
            int T = sc.nextInt();
            for(int t=1;t<=T;t++){
                int N = sc.nextInt();
                SkyLine skyLine = new SkyLine(N);
                for(int i=0;i<N;i++){
                    int x1 = sc.nextInt(); int x2 = sc.nextInt();
                    int y = sc.nextInt();
                    skyLine.add(2*i, x1, y, 's');
                    skyLine.add((2*i)+1, x2, y, 'e');
                }
                skyLine.solve();
//                System.out.println(Arrays.toString(skyLine.result.toArray()));
//                String sout = out.nextLine();
                System.out.println("case #"+t);
                for(Point point : skyLine.result){
                    String result = point.x+" "+point.y;
//                    String stringOut = out.nextLine();
                    System.out.println(result);
//                    System.out.println("case "+t+" : "+result.equals(stringOut));
                }
            }
        }catch (IOException e){

        }


    }

    static class SkyLine{
        int N;
        Point[] points;
        List<Point> result;

        public SkyLine(int n) {
            N = 2*n;
            points = new Point[N];
            result = new ArrayList<Point>();
        }

        public void add(int i, int x, int y, char c){
            points[i] = new Point(x, y, c);
        }

        public void solve(){
            Arrays.sort(points);
//            System.out.println(Arrays.toString(points));

//            MyTree pq = new MyTree();

            MTree pq = new MTree();
            pq.insert(0, -1);
            int maxQ = 0;

            for(int i=0;i<points.length;i++){
                if(points[i].a=='s'){
                    pq.insert(points[i].y, i);
                    int maxNew = (Integer)pq.maxValue();
                    if(maxQ!=maxNew){
                        result.add(new Point(points[i].x, maxNew, 'x'));
                        maxQ = maxNew;
                    }
                }else if(points[i].a=='e'){
                    int h = points[i].y;
                    pq.delete(h);
                    int maxNew = (Integer)pq.maxValue();
                    if(maxQ!=maxNew){
                        result.add(new Point(points[i].x, maxNew, 'x'));
                        maxQ = maxNew;
                    }
                }
            }

        }
    }

    static class Node{
        Comparable val;  int key;
        Node left, right;

        public Node(Comparable val, int key) {
            this.val = val;
            this.key = key;
        }

        @Override
        public String toString() {
            return "("+left+"["+val+"]"+right+")";
        }
    }

    static class MTree{
        Node root;

        public Node insert(Comparable val, int key){
            return root= insert(root, val, key);
        }

        private Node insert(Node parent, Comparable val, int key) {
            if(parent==null){
                return new Node(val, key);
            }else{
                if(val.compareTo(parent.val)<0) parent.left = insert(parent.left, val, key);
                else if(val.compareTo(parent.val)>0) parent.right = insert(parent.right, val, key);
            }
            return parent;
        }

        private Node max(Node parent){
            if(parent.right==null) return parent;
            return max(parent.right);
        }

        private Node min(Node parent){
            if (parent.left==null) return parent;
            return min(parent.left);
        }

        public Node max(){
            return max(root);
        }

        public Comparable maxValue(){
            return max(root).val;
        }

        private Node deleteMin(Node parent){
            if(parent.left==null) return parent.right;
            parent.left=deleteMin(parent.left);
            return parent;
        }

        private Node delete(Node parent, Comparable val){
            if (parent==null) return null;
            int cmp = val.compareTo(parent.val);
            if(cmp<0) parent.left = delete(parent.left, val);
            if(cmp>0) parent.right = delete(parent.right, val);
            else{
                if(parent.right==null) return parent.left;
                if(parent.left==null) return parent.right;
                Node node = parent;
                parent = min(node.right);
                parent.right = deleteMin(node.right);
                parent.left = node.left;
            }
            return parent;
        }

        public void delete(Comparable val){
            root = delete(root, val);
        }
    }

    static class Point implements Comparable<Point>{
        int x,y;
        char a ='.';

        public Point(int x, int y, char a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x<o.x)return -1;
            if(this.x>o.x) return 1;
            else{
                return (this.a=='s' ? -this.y : this.y) - (o.a=='s' ? -o.y : o.y);
            }
        }

        @Override
        public String toString() {
            return "("+x+"-"+y+"-"+a+")";
        }
    }
}
