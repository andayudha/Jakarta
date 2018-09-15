/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package data_structure;

import java.util.Random;

/**
 * Created by anda on 2/24/2018.
 */
public class MapTest {

    public static void main(String[] args){
//        TrollMap<String, Integer> map = new TrollMap<String, Integer>();
        GeekHash<String, Integer> map = new GeekHash<String, Integer>();
        String key = "anda";
        int value = 99;
        map.put(key, value);
        for(int i=1;i<=999;i++){
            map.put(getSaltString(i), i);
        }
//        System.out.println("size : "+map.size());
        System.out.println("search "+key+" : "+map.get(key));
        map.remove("anda");
        int x = 359;
        String keyX = getSaltString(x);
        map.put(keyX, x);
        System.out.println("search "+key+" : "+map.get(key));
        System.out.println("search "+keyX+" : "+map.get(keyX));
    }

    private static String getSaltString(int seed) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random(seed);
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
