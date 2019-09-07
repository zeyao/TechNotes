package hungarianAlgo;

import java.util.HashSet;
import java.util.Set;

public class HungarianAlgo {
	static boolean[] visited;  //is node visited, backtrack for each search
	static int[] matched; // Two node is matched or not, matched[3] = 1 means node3 match with node 1;
	
	private static int findMaxConnection(Connection[] connections) {
		int count = 0;
		int[][] matrix = contructMatrix(connections);
		matched = new int[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			visited = new boolean[matrix.length];
			if (find(matrix, i)) {
				count++;
			}
		}
		return count;
	}
	
	private static boolean find(int[][] matrix, int i) {
		 //找到一个和节点存在边的点，并且该点在本轮中没有被访问过
		for (int j = 0; j < matrix.length; j++) {
			if (matrix[i][j] == 1 && !visited[j]) {
				visited[j] = true; //标记为匹配过
				if (matched[j] == 0 || find(matrix, matched[j])) { 
					//按照交替路的模式找增广路，增广路相对于交替路的特性是就是，第一个节点和最后一个节点都是未匹配过的节点
	                //matched[j] == 0 说明找到了一个未匹配节点，将所有匹配边变为未匹配边，将所有未匹配边变为匹配边，这样匹配边数会加1,这个交换过程通过DFS回溯实现
					matched[j] = i;
					matched[i] = j;
					return true;
				}
			}
		}
		return false;
	}
	
	private static int[][] contructMatrix(Connection[] connections) {
		Set<Integer> set = new HashSet<>();
		for (Connection conn : connections) {
			set.add(conn.node1);
			set.add(conn.node2);	
		}
		int count = set.size()+1;
		int[][] matrix = new int[count][count];
		for (Connection conn : connections) {
			matrix[conn.node1][conn.node2] = 1;
		}
		return matrix;
	}
	
    
	public static class Connection {
		Integer node1;
		Integer node2;

		public Connection(Integer node1, Integer node2) {
			this.node1 = node1;
			this.node2 = node2;
		}
	}
	
	public static void main(String[] args) {
		Connection c1 = new Connection(1,5);
		Connection c2 = new Connection(1,6);
		Connection c3 = new Connection(2,6);
		Connection c4 = new Connection(2,7);
		Connection c5 = new Connection(3,5);
		Connection c6 = new Connection(3,6);
		Connection c7 = new Connection(4,7);
		Connection[] connectionArr = {c1,c2,c3,c4,c5,c6,c7};
		System.out.println(findMaxConnection(connectionArr));
	}
}
