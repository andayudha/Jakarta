/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anda on 6/27/2018.
 */
public class BallContainer {
    // Complete the organizingContainers function below.
    static String organizingContainers(int[][] container) {
        int n = container.length;
        List<Integer> listBall = new ArrayList<Integer>();
        List<Integer> listContainer = new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            int sumBall = 0;
            int sumContainer = 0;
            for(int j = 0;j<n;j++){
                sumContainer = sumContainer + container[i][j];
                sumBall = sumBall + container[j][i];
            }
            listContainer.add(sumContainer);
            listBall.add(sumBall);
        }
        listContainer.removeAll(listBall);
        return listContainer.isEmpty()? "Possible" : "Impossible";
    }


    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        Scanner scanner = new Scanner(new File("BallContainer.txt"));
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] container = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] containerRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowItems[j]);
                    container[i][j] = containerItem;
                }
            }

            String result = organizingContainers(container);
            System.out.println(result);

        }


        scanner.close();
    }
}

