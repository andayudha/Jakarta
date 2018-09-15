/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anda on 6/23/2018.
 */
public class Wkk {

    public static void main(String[] args){
        System.out.println(Arrays.toString(oddNumbers(2,10)));
    }

    static int[] oddNumbers(int l, int r) {
        int x = l;
        int i = 0;
        int size = ((r-l)/2);
        int[] list = new int[size];
        while(x<=r){
            if(x%2==0)x++;
            list[i] = x;
            x+=2;
            i++;
        }
        return list;
    }
}
