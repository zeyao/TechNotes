package undirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphValidTree {
	/**
	 * Description
	 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
	 * write a function to check whether these edges make up a valid tree.
	 * 
	 * Lintcode 178 : https://www.lintcode.com/problem/graph-valid-tree/description
	 * Leetcode 261
	 * 
	 * 经典的无向图找有没有环的题目
	 * 
	 */
	
boolean hasCycle = false;
    
    public boolean validTree(int n, int[][] edges) {
        if (edges.length == 0) return false;

        Set<Integer> visited = new HashSet<>();
        int[] edgeVistied = new int[edges.length];
        
        int first = edges[0][0];
        dfs(visited, first, edges, edgeVistied);
        if (hasCycle) {
            return false;
        }
        return visited.size() == n;
    }
    
    private void dfs(Set<Integer> visited, int node, int[][] edges, int[] edgeVistied) {
        visited.add(node);
        List<Integer> adjNodeList = findAdjNodeList(node, edges, edgeVistied);
        for (Integer adj : adjNodeList) {
            if (visited.contains(adj)) {
                hasCycle = true;
                return;
            }
            else if(!hasCycle){
                dfs(visited, adj, edges, edgeVistied);
            }
        }
    }
    
    private List<Integer> findAdjNodeList(int node, int[][] edges, int[] edgeVistied) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < edges.length; i++) {
            if (edges[i][0] == node && edgeVistied[i] == 0) {
                list.add(edges[i][1]);
                edgeVistied[i] = 1;
            }
            if (edges[i][1] == node && edgeVistied[i] == 0) {
                list.add(edges[i][0]);
                edgeVistied[i] = 1;
            }
        }
        return list;
    }
}
