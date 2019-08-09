package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RottingOranges {
	/**
	 * LC 994 https://leetcode.com/problems/rotting-oranges/
	 * 
	 */
	
	  public int orangesRotting(int[][] grid) {
	        //using List<Integer> as to store the rottened index i and j;
	        Queue<List<Integer>> queue = new LinkedList<>();
	        for (int i = 0 ; i < grid.length; i++) {
	            for (int j = 0; j < grid[0].length; j++) {
	                if (grid[i][j] == 2) {
	                    List<Integer> indexList = new ArrayList<>();
	                    indexList.add(i);
	                    indexList.add(j);
	                    queue.offer(indexList);
	                }
	            }
	        }
	        
	        int totalMinutes = 0;        
	        while (!queue.isEmpty()) {
	            int lastBfsQueueSize = queue.size();                 
	            while (lastBfsQueueSize > 0) {
	                List<Integer> indexList = queue.poll();
	                List<List<Integer>> adjRottenList = findAdjRotten(indexList, grid);
	                for (List<Integer> rottenIndex : adjRottenList) {
	                    int i = rottenIndex.get(0);
	                    int j = rottenIndex.get(1);
	                    grid[i][j] = 2;
	                    queue.offer(rottenIndex);
	                } 
	                lastBfsQueueSize--;
	            }
	            if (!queue.isEmpty()) {
	                totalMinutes++;     
	            }
	        }
	        
	        //如果BFS 结束，还有 1 说明无法达到， 无法把这个 1 变成 2
	        for (int i = 0 ; i < grid.length; i++) {
	            for (int j = 0; j < grid[0].length; j++) {
	                if (grid[i][j] == 1) {
	                    return -1;
	                }
	            }
	        }       
	        return totalMinutes;
	    }
	    
	    private List<List<Integer>> findAdjRotten(List<Integer> indexList, int[][] grid) {
	        int i = indexList.get(0);
	        int j = indexList.get(1);
	        List<List<Integer>> adjRottenList = new ArrayList<>();
	        if (i < grid.length - 1) {
	            if (grid[i+1][j] == 1) {
	                List<Integer> rottenIndexList = new ArrayList<>();
	                rottenIndexList.add(i+1);
	                rottenIndexList.add(j);
	                adjRottenList.add(rottenIndexList);
	            }
	            
	        }
	        
	        if (i > 0) {
	            if (grid[i-1][j] == 1) {
	                List<Integer> rottenIndexList = new ArrayList<>();
	                rottenIndexList.add(i-1);
	                rottenIndexList.add(j);
	                adjRottenList.add(rottenIndexList);
	            }
	            
	        }
	        
	        if (j > 0) {
	            if (grid[i][j-1] == 1) {
	                List<Integer> rottenIndexList = new ArrayList<>();
	                rottenIndexList.add(i);
	                rottenIndexList.add(j-1);
	                adjRottenList.add(rottenIndexList);
	            }
	            
	        }
	        
	        if (j < grid[0].length - 1) {
	            if (grid[i][j+1] == 1) {
	                List<Integer> rottenIndexList = new ArrayList<>();
	                rottenIndexList.add(i);
	                rottenIndexList.add(j+1);
	                adjRottenList.add(rottenIndexList);
	            }
	            
	        }
	        return adjRottenList;
	    }
}
