package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SRIN on 8/5/2015.
 */
public class UndirectedPath {

    boolean[] visited;
    int[] edgeTo;
    int source;

    public UndirectedPath(Graph g, int s) {
        this.visited = new boolean[g.N];
        this.edgeTo = new int[g.N];
        this.source = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        visited[s]=true;
        List<Integer> tetangga = g.getNeighbours(s);
        for(int w : tetangga){
            if(!visited[w]){
                edgeTo[w]=s;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return visited[v];
    }

    public List<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<Integer>();
        for(int x=v; x!=source; x=edgeTo[x]){
            path.add(x);
        }
        path.add(source);
        return path;

    }
}
