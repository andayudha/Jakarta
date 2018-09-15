/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by anda on 8/17/2016.
 */
public class KIngQuest {
    public static void main(String[] args){
        try {
            Scanner sc  = new Scanner(new File("king_quest.txt"));
            int tc = sc.nextInt();
            for(int t=1;t<=tc;t++){
                int n = sc.nextInt();
                int[] orcs = new int[n];
                int[] price = new int[n];
                for(int i=0;i<n;i++){
                    orcs[i] = sc.nextInt();
                    price[i]=sc.nextInt();
                }
                Solve s = new Solve(n, orcs, price);
                s.solve();
                System.out.println(t+" "+s.result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Solve {
        int n;
        int[] orcs, price;
        int result = Integer.MAX_VALUE;

        public Solve(int n, int[] orcs, int[] price) {
            this.n = n;
            this.orcs = orcs;
            this.price = price;
        }

        public void solve() {
            int total = 0;
            int[] squad = new int[n];
            int[] battle = new int[n];
            int[] state = new int[n];
            simulate(0, squad, battle, state, total);
        }

        private void simulate(int i, int[] squad, int[] battle, int[] state, int total) {
            if(i>=n){
                log(Arrays.toString(state)+" : "+total);
                result = Math.min(result, total);
                return;
            }

            //pass
            int totalPass = total + price[i];
            simulate(i+1, squad, battle, state, totalPass);

            //hire
            int totalHire = total + 2*price[i];
            int[] tempState = state.clone();
            tempState[i] = 1;
            int[] tempSquad = squad.clone();
            tempSquad[i] = tempSquad[i]+orcs[i];
            simulate(i+1, tempSquad, battle, tempState, totalHire);

            int totalArmy = getTotalArmy(squad);
            int enemy = orcs[i];
            if(totalArmy>=enemy){ //battle
                int idx = 0;
                int[] battleSquad = squad.clone();
                int[] tempBattle = battle.clone();
                int[] battleState = state.clone();
                while (enemy>0){
                    int army = squad[idx];
                    if(army<enemy){
                        enemy = enemy - army;
                        battleSquad[idx] = 0;
                    }else{
                        enemy = 0;
                        battleSquad[idx] = army - enemy;
                        tempBattle[idx] = tempBattle[idx]+1;
                        if(tempBattle[idx]>=3){
                            battleSquad[idx] = 0;
                            tempBattle[idx] = 0;
                        }
                    }
                    idx = idx+1;
                }
                battleState[i]=2;
                simulate(i+1, battleSquad, tempBattle, battleState, total);
            }
        }

        int getTotalArmy(int[] squad){
            int total = 0;
            for(int s : squad) total = total + s;
            return total;
        }

        private void log(String s) {
            System.out.println(s);
        }

    }
}
