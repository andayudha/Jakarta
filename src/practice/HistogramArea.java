package practice;

import data_structure.MyStack;

import java.util.Stack;

/**
 * Created by anda on 2/13/2016.
 */
public class HistogramArea  {

    public static void main(String[] args){
        int[] input = new int[]{2,1,2};
        int[] input2 = new int[]{2,1,2,3,1};
        int[] input3 = new int[]{1,2,4};
        int hist[] = {6, 2, 5, 4, 5, 1, 6};

        System.out.println(largestRectangleArea(input)==3);
        System.out.println(largestRectangleArea(input2)==5);
        System.out.println(largestRectangleArea(input3)==4);
        System.out.println(largestRectangleArea(hist)==12);
    }

    public static int largestRectangleArea(int[] heights) {
        int max = -1;
        int n = heights.length;
        MyStack stack = new MyStack(n);
        int poppedBar;
        int i;

        for (i=0;i<n;){
            int peek=0;
            if(!stack.isEmpty()) {
                peek = (Integer)stack.peek();
            }
            if(stack.isEmpty() || heights[i]>=heights[peek]){
                stack.push(i);
                i++;
            }else{
                poppedBar = (Integer) stack.pop();
                int area;
                if(stack.isEmpty()){
                    area = heights[poppedBar]*i;
                }else{
                    peek = (Integer)stack.peek();
                    area = heights[poppedBar]*(i-peek-1);
                }
                max = Math.max(max, area);
            }
        }
        while(!stack.isEmpty()){
            poppedBar = (Integer) stack.pop();
            int area;
            if(stack.isEmpty()){
                area = heights[poppedBar]*i;
            }else{
                int peek = (Integer)stack.peek();
                area = heights[poppedBar]*(i-peek-1);
            }
            max = Math.max(max, area);
        }
        return max;
    }
}
