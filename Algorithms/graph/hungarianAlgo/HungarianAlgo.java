package hungarianAlgo;

import java.util.Arrays;
/**
 * 
 * @author Liu - https://leetcode-cn.com/problems/broken-board-dominoes/
 *
 */

public class HungarianAlgo {
	 private int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

	    public int domino(int n, int m, int[][] broken) {
	        boolean [][] board = new boolean[n][m];
	        boolean [] visited = new boolean[m * n];
	        int[] link = new int[m * n]; // i connect with link[i]
	        Arrays.fill(link, -1);
	        for (boolean[] arr : board) {
	            Arrays.fill(arr, true);
	        }
	        for (int[] b : broken) {
	            board[b[0]][b[1]] = false;
	        }
	        // hungary
	        int res = 0;
	        for (int i = 0; i < n; i++) {
	            for (int j = (i % 2 == 0 ? 1 : 0); j < m; j+= 2) {
	                //构建棋盘格， 做成二分图，只从其中一个颜色开始匹配
	                if (board[i][j]) {
	                    Arrays.fill(visited, false);
	                    if (find(i, j, board, visited, link)) {
	                        res++; //找到增广路
	                    }
	                }
	            }
	        }
	        return res;
	    }

	    private boolean find(int row, int col, boolean [][] board, boolean [] visited, int[] link) {
	        int n = board.length;
	        int m = board[0].length;
	        for (int[] dir : dirs) {
	            int i = row + dir[0];
	            int j = col + dir[1]; //上下左右四个点可以做匹配
	            if (i < 0 || i >= n || j < 0 || j >= m) {
	                continue;
	            }
	            int node = m * i + j;
	            if (board[i][j] && !visited[node]) {
	                visited[node] = true;
	                if (link[node] == -1)  { //node and current node is not linked ,找到增广路
	                    link[node] = row * m + col;
	                    return true;
	                }
	                else { //node  和 link[node] 已经连接
	                    int nextRow = link[node] / m;
	                    int newCol = link[node] % m;                  
	                    if(find(nextRow, newCol, board, visited, link)) {
	                        link[node] = row * m + col; //替换匹配边
	                        return true;
	                    }
	                }
	            }
	        }
	        return false;
	    }

}
